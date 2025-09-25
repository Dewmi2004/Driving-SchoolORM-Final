package lk.ijse.drivingschoolormfinal.bo.custom;

import lk.ijse.drivingschoolormfinal.bo.SuperBO;
import lk.ijse.drivingschoolormfinal.entity.Student;

import java.util.List;

public interface QuaryBO extends SuperBO {
    List<Student> findStudentsInAllCourses();
}
