package lk.ijse.drivingschoolormfinal.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CourseTM {
    private long courseId;
    private String courseName;
    private String courseDuration;
    private String courseFee;

    public CourseTM(String courseName, String courseDuration, String courseFee) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
    }
}
