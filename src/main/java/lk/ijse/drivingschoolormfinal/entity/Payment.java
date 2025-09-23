package lk.ijse.drivingschoolormfinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String method;
    @Column(nullable = false)
    private String amount;

    public Payment(Date date, String method, String amount, Student student, Course course) {
        this.date = date;
        this.method = method;
        this.amount = amount;
        this.student = student;
        this.course = course;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    // 1:1 with Course (owning side)
    @OneToOne
    @JoinColumn(name = "course_id", unique = true, nullable = false)
    private Course course;
}
