package lk.ijse.drivingschoolormfinal.config;

import lk.ijse.drivingschoolormfinal.entity.Instructor;
import lk.ijse.drivingschoolormfinal.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration instance;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {

        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Instructor.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (instance == null) ? instance = new FactoryConfiguration()
                : instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}

