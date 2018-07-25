package com.ef;

import com.ef.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.NoResultException;
import javax.persistence.metamodel.Type;
import java.util.logging.Level;

public class HibernateUtil {
    private static SessionFactory factory;
    private static HibernateUtil ourInstance = new HibernateUtil();

    public static HibernateUtil getInstance() {
        return ourInstance;
    }

    private HibernateUtil() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.username", System.getenv("LOG_PARSER_USER"));
            configuration.setProperty("hibernate.connection.password", System.getenv("LOG_PARSER_PASS"));
            configuration.setProperty("hibernate.connection.url", System.getenv("LOG_PARSER_URL"));
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperty("hibernate.show_sql", "false");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.generate_statistics", "false");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            configuration.addPackage("com.ef.entities");
            factory = configuration
                    .addAnnotatedClass(IpAddressEntity.class)
                    .addAnnotatedClass(AgentEntity.class)
                    .addAnnotatedClass(LogEntity.class)
                    .addAnnotatedClass(MethodProtocolEntity.class)
                    .addAnnotatedClass(BlockLogEntity.class)
                    .buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There is issue in hibernate util");
        }
    }

    public static Session getSession() {
        return factory.openSession();
    }

    public static IpAddressEntity findOrCreateIp(String value) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            String query = "from IpAddressEntity where value = :value";
            return session
                    .createQuery(query, IpAddressEntity.class)
                    .setParameter("value", value)
                    .getSingleResult();
        } catch (NoResultException e) {
            IpAddressEntity entity = new IpAddressEntity();
            entity.setValue(value);
            session.save(entity);
            tx.commit();
            return entity;
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static AgentEntity findOrCreateAgent(String value) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            String query = "from AgentEntity where value = :val";
            return session
                    .createQuery(query, AgentEntity.class)
                    .setParameter("val", value)
                    .getSingleResult();
        } catch (NoResultException e) {
            AgentEntity entity = new AgentEntity();
            entity.setValue(value);
            session.save(entity);
            tx.commit();
            return entity;
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static MethodProtocolEntity findOrCreateMethodProtocol(String value) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            String query = "from MethodProtocolEntity where value = :val";
            return session
                    .createQuery(query, MethodProtocolEntity.class)
                    .setParameter("val", value)
                    .getSingleResult();
        } catch (NoResultException e) {
            MethodProtocolEntity entity = new MethodProtocolEntity();
            entity.setValue(value);
            session.save(entity);
            tx.commit();
            return entity;
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
