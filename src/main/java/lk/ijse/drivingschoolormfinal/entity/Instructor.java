package lk.ijse.drivingschoolormfinal.entity;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "instructors")
public class Instructor extends SuperEntity{
    @Id
    @Column(name = "instructor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long instructorID;
    @Column(nullable = false)
    private String instructorName;
    @Column(nullable = false, unique = true)
    private String instructorEmail;
    @Column(length = 15)
    private String instructorPhone;
    @Column(nullable = false)
    private String instructorAvailability;

    public Instructor(String instructorName, String instructorEmail, String instructorPhone, String instructorAvailability) {
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.instructorAvailability = instructorAvailability;
    }
}
