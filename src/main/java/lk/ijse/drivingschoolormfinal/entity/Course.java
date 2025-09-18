package lk.ijse.drivingschoolormfinal.entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;
    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    private String courseDuration;
    @Column(nullable = false)
    private String courseFee;

    public Course(String courseName, String courseDuration, String courseFee) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
    }
}
