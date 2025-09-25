package lk.ijse.drivingschoolormfinal.dao.custom;

import lk.ijse.drivingschoolormfinal.dao.SuperDao;
import lk.ijse.drivingschoolormfinal.entity.Student;

import java.util.List;

public interface QuaryDAO extends SuperDao {
    public List<Student> findStudentsInAllCourses();
}
