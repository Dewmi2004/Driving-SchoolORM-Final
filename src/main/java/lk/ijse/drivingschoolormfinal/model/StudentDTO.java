package lk.ijse.drivingschoolormfinal.model;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StudentDTO {
    private long studentID;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private String studentAddress;
    private String registerFee;
    private Date registerDate;

    public StudentDTO(String studentName, String studentEmail, String studentPhone, String studentAddress, String registerFee, Date registerDate) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.registerFee = registerFee;
        this.registerDate = registerDate;
    }

    public StudentDTO(long studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }
}
