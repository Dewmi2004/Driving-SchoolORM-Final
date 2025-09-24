package lk.ijse.drivingschoolormfinal.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private Long userID;
    private String userName;
    private String userPassword;
    private String userRole;

    public UserDTO(String userName, String userPassword, String userRole) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
}
