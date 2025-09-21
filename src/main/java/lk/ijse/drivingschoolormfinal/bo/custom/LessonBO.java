package lk.ijse.drivingschoolormfinal.bo.custom;

import lk.ijse.drivingschoolormfinal.bo.SuperBO;
import lk.ijse.drivingschoolormfinal.model.LessonDTO;

import java.util.List;

public interface LessonBO extends SuperBO {
    boolean saveLesson(LessonDTO dto) throws Exception;

    boolean updateLesson(LessonDTO dto) throws Exception;

    boolean deleteLesson(String id) throws Exception;

    List<LessonDTO> findAll() throws Exception;
}
