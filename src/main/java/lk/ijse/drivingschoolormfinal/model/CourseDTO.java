package lk.ijse.drivingschoolormfinal.model;
import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private long courseId;
    private String courseName;
    private String courseDuration;
    private String courseFee;
    private Button btn;

    public CourseDTO(long courseId, String courseName, String courseDuration, String courseFee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
    }

    public CourseDTO(String courseName, String courseDuration, String courseFee) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
    }

}
