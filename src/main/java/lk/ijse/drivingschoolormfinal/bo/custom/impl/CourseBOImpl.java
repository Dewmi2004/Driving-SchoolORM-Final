package lk.ijse.drivingschoolormfinal.bo.custom.impl;

import lk.ijse.drivingschoolormfinal.bo.custom.CourseBO;
import lk.ijse.drivingschoolormfinal.bo.custom.InstructorBO;
import lk.ijse.drivingschoolormfinal.dao.DAOFactory;
import lk.ijse.drivingschoolormfinal.dao.custom.CourseDAO;
import lk.ijse.drivingschoolormfinal.dao.custom.InstructorDAO;
import lk.ijse.drivingschoolormfinal.entity.Course;
import lk.ijse.drivingschoolormfinal.entity.Instructor;
import lk.ijse.drivingschoolormfinal.model.CourseDTO;
import lk.ijse.drivingschoolormfinal.model.InstructorDTO;

import java.util.List;
import java.util.stream.Collectors;


public class CourseBOImpl implements CourseBO {
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.COURSE);

    @Override
    public boolean saveCourse(CourseDTO dto) throws Exception {
        Course course = new Course(
                dto.getCourseName(),
                dto.getCourseDuration(),
                dto.getCourseFee()
        );
        return courseDAO.save(course);
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
        Course course = new Course(
                dto.getCourseId(),
                dto.getCourseName(),
                dto.getCourseDuration(),
                dto.getCourseFee()
        );
            return courseDAO.update(course);
        }


        @Override
    public boolean deleteCourse(String id) throws Exception {
        return courseDAO.delete(id);
    }

    @Override
    public List<CourseDTO> findAll() throws Exception {
        return courseDAO.findAll().stream().map(course ->
                new CourseDTO(
                        course.getCourseId(),
                        course.getCourseName(),
                        course.getCourseDuration(),
                        course.getCourseFee()
                )).collect(Collectors.toList());
    }

    }


