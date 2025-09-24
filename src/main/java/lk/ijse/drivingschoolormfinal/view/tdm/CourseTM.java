package lk.ijse.drivingschoolormfinal.view.tdm;

import javafx.scene.control.Button;
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
    private Button btn;

    public CourseTM(String courseName, String courseDuration, String courseFee) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
    }
}
