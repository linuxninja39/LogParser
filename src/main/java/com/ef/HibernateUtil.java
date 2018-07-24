package com.ef;

import com.ef.entities.AgentEntity;
import com.ef.entities.IpAddressEntity;
import com.ef.entities.LogEntity;
import com.ef.entities.MethodProtocolEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory factory;
    private static HibernateUtil ourInstance = new HibernateUtil();

    public static HibernateUtil getInstance() {
        return ourInstance;
    }

    private HibernateUtil() {
        /*
        try {
            factory = new Configuration()
                    .configure()
                    .addPackage("com.ef.entities")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        */

        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.username", System.getenv("LOG_PARSER_USER"));
            configuration.setProperty("hibernate.connection.password", System.getenv("LOG_PARSER_PASS"));
            configuration.setProperty("hibernate.connection.url", System.getenv("LOG_PARSER_URL"));
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            configuration.addPackage("com.ef.entities");
            factory = configuration
                    .addAnnotatedClass(IpAddressEntity.class)
                    .addAnnotatedClass(AgentEntity.class)
                    .addAnnotatedClass(LogEntity.class)
                    .addAnnotatedClass(MethodProtocolEntity.class)
                    .buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There is issue in hibernate util");
        }
    }

    public static Session getSession() {
        return factory.openSession();
    }
}
