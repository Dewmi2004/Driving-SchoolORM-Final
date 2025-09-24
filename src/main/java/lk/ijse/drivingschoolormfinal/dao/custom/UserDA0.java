package lk.ijse.drivingschoolormfinal.dao.custom;

import lk.ijse.drivingschoolormfinal.dao.CrudDao;
import lk.ijse.drivingschoolormfinal.entity.User;

public interface UserDA0 extends CrudDao<User,String> {
    User findByUserName(String userName) throws Exception;

}
