package lk.ijse.drivingschoolormfinal.dao.custom.impl;

import lk.ijse.drivingschoolormfinal.config.FactoryConfiguration;
import lk.ijse.drivingschoolormfinal.dao.custom.QuaryDAO;
import lk.ijse.drivingschoolormfinal.entity.Student;
import org.hibernate.Session;

import java.util.List;

public class QuaryDAOImpl implements QuaryDAO {
    @Override
    public List<Student> findStudentsInAllCourses() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {

            String hql = "SELECT new lk.ijse.drivingschoolormfinal.model.StudentDTO(" +
                    "s.studentId, s.studentName, s.studentEmail) " +
                    "FROM students s " +
                    "WHERE NOT EXISTS (" +
                    "   SELECT c.courseId FROM courses c " +
                    "   WHERE c.courseId NOT IN (" +
                    "       SELECT sc.courseId FROM student_course sc WHERE sc.studentId = s.studentId" +
                    "   )" +
                    ")";

            return session.createQuery(hql, Student.class).getResultList();
        }
    }
    }







