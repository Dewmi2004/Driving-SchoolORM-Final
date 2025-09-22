package lk.ijse.drivingschoolormfinal.bo.custom;

import lk.ijse.drivingschoolormfinal.bo.SuperBO;
import lk.ijse.drivingschoolormfinal.model.CourseDTO;
import lk.ijse.drivingschoolormfinal.model.InstructorDTO;
import lk.ijse.drivingschoolormfinal.model.LessonDTO;
import lk.ijse.drivingschoolormfinal.model.StudentDTO;

import java.util.List;

public interface LessonBO extends SuperBO {
    boolean saveLesson(LessonDTO dto) throws Exception;

    boolean updateLesson(LessonDTO dto) throws Exception;

    boolean deleteLesson(String id) throws Exception;

    List<LessonDTO> findAll() throws Exception;


    List<String> getAllInstructorIds() throws Exception;
     List<String> getAllCourseIds() throws Exception;
     List<String> getAllStudentIds() throws Exception;
}
