package lk.ijse.drivingschoolormfinal.view.tdm;

import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LessonTM {
    private long lessonID;
    private Date date;
    private String time;
    private String status;
    private long studentID;
    private long courseID;
    private long instructorID;

    public LessonTM(Date date, String time, String status, long studentID, long courseID, long instructorID) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.studentID = studentID;
        this.courseID = courseID;
        this.instructorID = instructorID;
    }
}

