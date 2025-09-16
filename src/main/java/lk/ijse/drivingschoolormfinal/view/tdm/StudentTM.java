package lk.ijse.drivingschoolormfinal.view.tdm;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString

public class StudentTM {
    private long studentID;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private String studentAddress;
    private String registerFee;
    private Date registerDate;
}
