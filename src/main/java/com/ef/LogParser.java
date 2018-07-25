package com.ef;

import com.ef.entities.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class LogParser {
    private static String FILE_NAME = "access.log";

    List<?> run(CommandLineArgs args) throws IOException, URISyntaxException {
        if (isImportNeeded()) {
            Path file = findFile();
            Files.lines(file).forEach(this::handleLine);
        }

        List<?> ipEntities = findIps(args);

        saveBlockLog(ipEntities);

        return ipEntities;
    }

    private List<?> findIps(CommandLineArgs args) {
        Session session = HibernateUtil.getSession();
        Calendar cal = Calendar.getInstance();
        cal.setTime(args.getStartDate());
        if ((args.getDuration().equals("hourly"))) {
            cal.add(Calendar.HOUR_OF_DAY, 1);
        } else {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Date futureTime = cal.getTime();

        String query = "select ip, count(ip.id)" +
                "from IpAddressEntity ip left join ip.logs l " +
                "where l.timeStamp between :start and :end " +
                "group by ip.value " +
                "having count(ip.id) > :threshold";
        Query ipQuery = session.createQuery(query)
                .setParameter("threshold", args.getThreshold())
                .setParameter("start", args.getStartDate())
                .setParameter("end", futureTime);
        List<?> ips = ipQuery.getResultList();


        return ips;
    }

    private void saveBlockLog(List<?> results) {
        for (Object ipEntity : results) {
            Object[] row = (Object[]) ipEntity;
            IpAddressEntity ip = (IpAddressEntity) row[0];
            int count = (int) (long) row[1];

            Session session = HibernateUtil.getSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                BlockLogEntity blockLogEntity = new BlockLogEntity();
                blockLogEntity.setTimeStamp(new Date());
                blockLogEntity.setCount(count);
                blockLogEntity.setIpAddress(ip);
                blockLogEntity.setReason("too many requests");
                session.save(blockLogEntity);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                throw e;
            } finally {
                session.close();
            }

        }

    }

    private Boolean isImportNeeded() {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;


        try {
            tx = session.beginTransaction();
            String query = "from LogEntity order by timeStamp";
            session.createQuery(query, LogEntity.class).setMaxResults(1).getSingleResult();
            tx.commit();
        } catch (NoResultException e) {
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }


        return false;
    }

    private Path findFile() throws URISyntaxException {
        Path sameDir = Paths.get(LogParser.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        Path logFile = Paths.get(sameDir.toString(), FILE_NAME);
        if (sameDir.toString().toLowerCase().endsWith(".jar")) {
            logFile = Paths.get(sameDir.toString(), "../", FILE_NAME);
        }
        return logFile;
    }

    private void handleLine(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 5) return;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        IpAddressEntity ip = HibernateUtil.findOrCreateIp(parts[1]);
        AgentEntity agent = HibernateUtil.findOrCreateAgent(parts[4]);
        MethodProtocolEntity methodProtocol = HibernateUtil.findOrCreateMethodProtocol(parts[2]);

        try {
            tx = session.beginTransaction();
            LogEntity log = new LogEntity();

            log.setAgent(agent);
            log.setIpAddress(ip);
            log.setMethodProtocol(methodProtocol);
            Date timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(parts[0]);
            log.setTimeStamp(timeStamp);
            log.setStatusCode(Integer.parseInt(parts[3]));
            session.save(log);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
