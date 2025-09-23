package lk.ijse.drivingschoolormfinal.dao.custom.impl;

import lk.ijse.drivingschoolormfinal.config.FactoryConfiguration;
import lk.ijse.drivingschoolormfinal.dao.custom.PaymentDAO;
import lk.ijse.drivingschoolormfinal.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Payment entity) {
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
    public boolean update(Payment entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            Payment payment = session.get(Payment.class, Long.parseLong(id));
            if (payment != null) {
                payment.setStudent(null);
                payment.setCourse(null); // Prevent FK constraint errors when deleting
                session.remove(payment); // Remove payment
            }
            tx.commit();
            return payment != null;
        }
    }

    @Override
    public List<Payment> findAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Payment> list = session.createQuery("FROM Payment", Payment.class).list();
        session.close();
        return list;
    }
}