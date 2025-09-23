package lk.ijse.drivingschoolormfinal.bo.custom.impl;

import lk.ijse.drivingschoolormfinal.bo.custom.PaymentBO;
import lk.ijse.drivingschoolormfinal.dao.DAOFactory;
import lk.ijse.drivingschoolormfinal.dao.custom.CourseDAO;
import lk.ijse.drivingschoolormfinal.dao.custom.PaymentDAO;
import lk.ijse.drivingschoolormfinal.dao.custom.StudentDAO;
import lk.ijse.drivingschoolormfinal.entity.Course;
import lk.ijse.drivingschoolormfinal.entity.Payment;
import lk.ijse.drivingschoolormfinal.entity.Student;
import lk.ijse.drivingschoolormfinal.model.PaymentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentBOImpl implements PaymentBO {
    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.PAYMENT);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.STUDENT);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.COURSE);

    @Override
    public boolean savePayment(PaymentDTO dto) throws Exception {
        // Fetch existing Student and Course from DB to avoid detached entity errors
        Student student = studentDAO.findById(dto.getStudentID());
        Course course = courseDAO.findById(dto.getCourseID());
        Payment payment = new Payment(dto.getDate(), dto.getMethod(), dto.getAmount(), student, course);
        return paymentDAO.save(payment);
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(dto.getStudentID());
        Course course = courseDAO.findById(dto.getCourseID());
        Payment payment = new Payment(dto.getPaymentId(), dto.getDate(), dto.getMethod(), dto.getAmount(), student, course);
        return paymentDAO.update(payment);
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public List<PaymentDTO> findAll() throws Exception {
        return paymentDAO.findAll().stream().map(payment ->
                new PaymentDTO(
                        payment.getPaymentId(),
                        payment.getDate(),
                        payment.getMethod(),
                        payment.getAmount(),
                        payment.getStudent().getStudentID(),
                        payment.getCourse().getCourseId()
                )).collect(Collectors.toList());
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        ArrayList<Payment> payments = (ArrayList<Payment>) paymentDAO.findAll();
        ArrayList<PaymentDTO> dtos = new ArrayList<>();
        for (Payment p : payments) {
            dtos.add(new PaymentDTO(p.getPaymentId(), p.getDate(), p.getMethod(), p.getAmount(), p.getStudent().getStudentID(), p.getCourse().getCourseId()));
        }
        return dtos;
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        List<Student> list = studentDAO.findAll();
        List<String> idList = new ArrayList<>();
        for (Student s : list) {
            idList.add(String.valueOf(s.getStudentID()));
        }
        return idList;
    }

    @Override
    public List<String> getAllCourseIds() throws Exception {
        List<Course> list = courseDAO.findAll();
        List<String> idList = new ArrayList<>();
        for (Course c : list) {
            idList.add(String.valueOf(c.getCourseId()));
        }
        return idList;
    }
}