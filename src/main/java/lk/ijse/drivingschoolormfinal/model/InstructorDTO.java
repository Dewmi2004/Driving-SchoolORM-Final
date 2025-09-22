package lk.ijse.drivingschoolormfinal.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO {
    private long instructorID;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhone;
    private String instructorAvailability;

    public InstructorDTO(String instructorName, String instructorEmail, String instructorPhone, String instructorAvailability) {
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.instructorAvailability = instructorAvailability;
    }

    public InstructorDTO(long instructorID, String instructorName) {
        this.instructorID = instructorID;
        this.instructorName = instructorName;
    }
}
