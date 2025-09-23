package lk.ijse.drivingschoolormfinal.model;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentDTO {
    private long paymentId;
    private Date date;
    private String method;
    private String amount;
    private long studentID;
    private long courseID;

    public PaymentDTO(Date date, String method, String amount, long studentID, long courseID) {
        this.date = date;
        this.method = method;
        this.amount = amount;
        this.studentID = studentID;
        this.courseID = courseID;
    }
}
