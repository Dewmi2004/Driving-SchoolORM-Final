package lk.ijse.drivingschoolormfinal.bo.custom.impl;

import lk.ijse.drivingschoolormfinal.bo.custom.UserBO;
import lk.ijse.drivingschoolormfinal.dao.DAOFactory;
import lk.ijse.drivingschoolormfinal.dao.custom.UserDA0;
import lk.ijse.drivingschoolormfinal.entity.User;
import lk.ijse.drivingschoolormfinal.model.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserBOImpl implements UserBO {

    private final UserDA0 userDAO = (UserDA0) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.USER);

    @Override
    public boolean saveUser(UserDTO dto) throws Exception {
        User user = new User(
                dto.getUserName(),
                dto.getUserPassword(),
                dto.getUserRole()
        );
        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        User user = new User(
                dto.getUserID(),
                dto.getUserName(),
                dto.getUserPassword(),
                dto.getUserRole()
        );

        return userDAO.update(user);        }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public List<UserDTO> findAll() throws Exception {
        return userDAO.findAll().stream().map(user ->
                new UserDTO(
                        user.getUserID(),
                        user.getUserName(),
                        user.getUserPassword(),
                        user.getUserRole()
                )).collect(Collectors.toList());    }

    @Override
    public ArrayList<UserDTO> getAllUser() throws Exception {
        ArrayList<User> users = (ArrayList<User>) userDAO.findAll();

        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User c : users) {
            userDTOS.add(new UserDTO(c.getUserID(), c.getUserName(), c.getUserPassword(), c.getUserRole()));
        }
        return userDTOS;
    }

    @Override
    public UserDTO findByUserName(String userName) throws Exception {
        User user = userDAO.findByUserName(userName);
        if (user != null) {
            return new UserDTO(user.getUserID(), user.getUserName(), user.getUserPassword(), user.getUserRole());
        }
        return null;
    }
}
