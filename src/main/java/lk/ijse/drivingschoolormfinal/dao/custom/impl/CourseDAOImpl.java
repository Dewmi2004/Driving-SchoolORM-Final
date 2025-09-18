package lk.ijse.drivingschoolormfinal.dao.custom.impl;

import lk.ijse.drivingschoolormfinal.config.FactoryConfiguration;
import lk.ijse.drivingschoolormfinal.dao.custom.CourseDAO;
import lk.ijse.drivingschoolormfinal.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

        @Override
        public boolean save(Course entity) {
            try (Session session = factoryConfiguration.getSession()) {
                Transaction tx = session.beginTransaction();
                session.persist(entity);
                tx.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }


        @Override
    public boolean update(Course entity) throws Exception {
            Session session = factoryConfiguration.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
            session.close();
            return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        Course course = session.get(Course.class, s);
        if (course != null) {
            session.remove(course);
        }
        tx.commit();
        session.close();
        return course != null;
    }



    @Override
    public List<Course> findAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Course> list = session.createQuery("FROM Course", Course.class).list();
        session.close();
        return list;
    }

}
