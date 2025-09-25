package lk.ijse.drivingschoolormfinal.bo.custom.impl;

import lk.ijse.drivingschoolormfinal.bo.custom.QuaryBO;
import lk.ijse.drivingschoolormfinal.dao.DAOFactory;
import lk.ijse.drivingschoolormfinal.dao.custom.QuaryDAO;
import lk.ijse.drivingschoolormfinal.entity.Student;

import java.util.List;

public class QuaryBOImpl implements QuaryBO {
QuaryDAO quaryDAO = (QuaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.QUARY);
    @Override
    public List<Student> findStudentsInAllCourses() {
        return quaryDAO.findStudentsInAllCourses();

    }
}
