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
@Table(name = "lessons")
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lessonID;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private String status;

}