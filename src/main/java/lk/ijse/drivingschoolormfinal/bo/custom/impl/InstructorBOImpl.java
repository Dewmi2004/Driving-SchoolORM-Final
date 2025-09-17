package lk.ijse.drivingschoolormfinal.bo.custom.impl;

import lk.ijse.drivingschoolormfinal.bo.custom.InstructorBO;
import lk.ijse.drivingschoolormfinal.bo.custom.StudentBO;
import lk.ijse.drivingschoolormfinal.dao.DAOFactory;
import lk.ijse.drivingschoolormfinal.dao.custom.InstructorDAO;
import lk.ijse.drivingschoolormfinal.dao.custom.StudentDAO;
import lk.ijse.drivingschoolormfinal.entity.Instructor;
import lk.ijse.drivingschoolormfinal.entity.Student;
import lk.ijse.drivingschoolormfinal.model.InstructorDTO;
import lk.ijse.drivingschoolormfinal.model.StudentDTO;

import java.util.List;
import java.util.stream.Collectors;


public class InstructorBOImpl implements InstructorBO {
    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.INSTRUCTOR);

    @Override
    public boolean saveInstructor(InstructorDTO dto) throws Exception {
        Instructor instructor = new Instructor(
                dto.getInstructorName(),
                dto.getInstructorEmail(),
                dto.getInstructorPhone(),
                dto.getInstructorAvailability()
        );
        return instructorDAO.save(instructor);
    }

    @Override
    public boolean updateInstructor(InstructorDTO dto) throws Exception {
            Instructor instructor = new Instructor(
                    dto.getInstructorID(),
                    dto.getInstructorName(),
                    dto.getInstructorEmail(),
                    dto.getInstructorPhone(),
                    dto.getInstructorAvailability()
            );
            return instructorDAO.update(instructor);
        }


        @Override
    public boolean deleteInstructor(String id) throws Exception {
        return instructorDAO.delete(id);
    }

    @Override
    public InstructorDTO findById(String id) throws Exception {
        Instructor instructor = instructorDAO.findById(id);
        if (instructor == null) return null;
        return new InstructorDTO(
              instructor.getInstructorID(),
                instructor.getInstructorName(),
                instructor.getInstructorEmail(),
                instructor.getInstructorPhone(),
                instructor.getInstructorAvailability()
        );
    }

    @Override
    public List<InstructorDTO> findAll() throws Exception {
        return instructorDAO.findAll().stream().map(instructor ->
                new InstructorDTO(
                        instructor.getInstructorID(),
                        instructor.getInstructorName(),
                        instructor.getInstructorEmail(),
                        instructor.getInstructorPhone(),
                        instructor.getInstructorAvailability()
                )).collect(Collectors.toList());
    }

    }


