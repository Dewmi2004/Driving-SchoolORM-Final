package lk.ijse.drivingschoolormfinal.bo.custom;

import lk.ijse.drivingschoolormfinal.bo.SuperBO;
import lk.ijse.drivingschoolormfinal.model.UserDTO;

import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO dto) throws Exception;
    boolean updateUser(UserDTO dto) throws Exception;
    boolean deleteUser(String id) throws Exception;
    List<UserDTO> findAll() throws Exception;

    ArrayList<UserDTO> getAllUser() throws Exception;

    UserDTO findByUserName(String userName) throws Exception;

}
