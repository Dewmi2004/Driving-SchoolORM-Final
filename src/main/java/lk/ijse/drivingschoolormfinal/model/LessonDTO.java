package lk.ijse.drivingschoolormfinal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LessonDTO {
    private long lessonID;
    private Date date;
    private String time;
    private String status;
    private long studentID;
    private long courseID;
    private long instructorID;
}