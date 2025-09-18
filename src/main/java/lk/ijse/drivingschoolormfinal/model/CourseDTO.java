package lk.ijse.drivingschoolormfinal.model;
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

    public CourseDTO(String courseName, String courseDuration, String courseFee) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseFee = courseFee;
    }
}
