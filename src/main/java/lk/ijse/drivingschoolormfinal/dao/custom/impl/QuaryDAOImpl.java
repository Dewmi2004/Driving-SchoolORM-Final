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

            String hql = "SELECT new lk.ijse.drivingschoolormfinal.entity.Student(" +
                    "s.studentID, s.studentName, s.studentEmail) " +
                    "FROM Student s " +
                    "WHERE NOT EXISTS (" +
                    "   SELECT c.courseId FROM Course c " +
                    "   WHERE c.courseId NOT IN (" +
                    "       SELECT l.course.courseId FROM Lesson l WHERE l.student.studentID = s.studentID" +
                    "   )" +
                    ")";

            return session.createQuery(hql, Student.class)
                    .getResultList();
        }
    }




}







