package lk.ijse.drivingschoolormfinal.dao.custom;

import lk.ijse.drivingschoolormfinal.dao.CrudDao;
import lk.ijse.drivingschoolormfinal.entity.Instructor;

import java.util.List;

public interface InstructorDAO extends CrudDao<Instructor, String>{
    public Instructor findById(long id) throws Exception;
}
