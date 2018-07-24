package com.ef;

import com.ef.entities.IpAddressEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;

class LogParser {
    private static String FILE_NAME = "access.log";
    private Session session;

    String run(CommandLineArgs args) throws IOException, URISyntaxException {
        Path file = findFile();
        System.out.println(file);
        session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            IpAddressEntity ip = new IpAddressEntity();
            ip.setValue("1.1.1.1");
            Integer id = (Integer) session.save(ip);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

        //Files.lines(file).forEach(this::handleLine);
        return "handleLine n stuffz";
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
    }
}
