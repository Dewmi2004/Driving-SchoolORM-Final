package lk.ijse.drivingschoolormfinal.bo.custom;

import lk.ijse.drivingschoolormfinal.bo.SuperBO;
import lk.ijse.drivingschoolormfinal.model.CourseDTO;
import lk.ijse.drivingschoolormfinal.model.InstructorDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    boolean saveCourse(CourseDTO dto) throws Exception;
    boolean updateCourse(CourseDTO dto) throws Exception;
    boolean deleteCourse(String id) throws Exception;
    List<CourseDTO> findAll() throws Exception;

}
