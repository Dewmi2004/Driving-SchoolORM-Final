package lk.ijse.drivingschoolormfinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "students")
public class Student extends SuperEntity {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentID;
    @Column(nullable = false)
    private String studentName;
    @Column(nullable = false, unique = true)
    private String studentEmail;
    @Column(length = 15)
    private String studentPhone;
    @Column(nullable = false)
    private String studentAddress;
    @Column(name = "registerFee")
    private String registerFee;
    @Column(nullable = false)
    private Date registerDate;

    public Student(String studentName, String studentEmail, String studentPhone, String studentAddress, String registerFee, Date registerDate) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.registerFee = registerFee;
        this.registerDate = registerDate;
    }
}
