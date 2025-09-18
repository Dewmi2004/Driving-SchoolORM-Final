package lk.ijse.drivingschoolormfinal.bo.custom;

import lk.ijse.drivingschoolormfinal.bo.SuperBO;
import lk.ijse.drivingschoolormfinal.model.InstructorDTO;
import lk.ijse.drivingschoolormfinal.model.StudentDTO;

import java.util.List;

public interface InstructorBO extends SuperBO {
    boolean saveInstructor(InstructorDTO dto) throws Exception;
    boolean updateInstructor(InstructorDTO dto) throws Exception;
    boolean deleteInstructor(String id) throws Exception;
    List<InstructorDTO> findAll() throws Exception;

}
