package lk.ijse.drivingschoolormfinal.dao.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lk.ijse.drivingschoolormfinal.dao.custom.UserDA0;
import lk.ijse.drivingschoolormfinal.config.FactoryConfiguration;
import lk.ijse.drivingschoolormfinal.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDA0 {
    @Override
    public boolean save(User entity) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
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
    public boolean update(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;    }

    @Override
    public boolean delete(String c) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, c);
        if (user != null) {
            session.remove(user);
        }
        tx.commit();
        session.close();
        return user != null;
    }


    @Override
    public List<User> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<User> list = session.createQuery("FROM User ", User.class).list();
        session.close();
        return list;
    }

    @Override
    public User findByUserName(String userName) throws Exception {
        EntityManager em = FactoryConfiguration.getInstance().getSession();
        try {
            Query query = (Query) em.createQuery("SELECT u FROM User u WHERE u.userName = :username");
            query.setParameter("username", userName);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
