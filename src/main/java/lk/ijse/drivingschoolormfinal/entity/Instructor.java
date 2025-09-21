package lk.ijse.drivingschoolormfinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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

    public Instructor(long instructorID, String instructorName, String instructorEmail, String instructorPhone, String instructorAvailability) {
        this.instructorID = instructorID;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.instructorAvailability = instructorAvailability;
    }

    // 🔹 One instructor can have many lessons
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();

}
