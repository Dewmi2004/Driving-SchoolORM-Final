package lk.ijse.drivingschoolormfinal.bo.custom.impl;

import lk.ijse.drivingschoolormfinal.bo.custom.CourseBO;
import lk.ijse.drivingschoolormfinal.dao.DAOFactory;
import lk.ijse.drivingschoolormfinal.dao.custom.CourseDAO;
import lk.ijse.drivingschoolormfinal.entity.Course;
import lk.ijse.drivingschoolormfinal.model.CourseDTO;

import java.util.ArrayList;
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
    @Override
    public ArrayList<CourseDTO> getAllCourse() throws Exception {
        ArrayList<Course> course = (ArrayList<Course>) courseDAO.findAll();

        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course c : course) {
            courseDTOS.add(new CourseDTO(c.getCourseId(),c.getCourseName(),c.getCourseDuration(),c.getCourseFee()));
        }
        return courseDTOS;

    }

    @Override
    public CourseDTO findById(long courseId) throws Exception {
        Course course = courseDAO.findById(courseId);
        if (course == null) return null;

        return new CourseDTO(
                course.getCourseId(),
                course.getCourseName(),
                course.getCourseDuration(),
                course.getCourseFee()
        );
    }
}


