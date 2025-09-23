package lk.ijse.drivingschoolormfinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    public Student(long studentID, String studentName, String studentEmail, String studentPhone, String studentAddress, String registerFee, Date registerDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.registerFee = registerFee;
        this.registerDate = registerDate;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn( name = "student_id")
    private List<Payment> payments = new ArrayList<>();


}
