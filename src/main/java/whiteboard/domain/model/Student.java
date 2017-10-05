package whiteboard.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "Students")
public class Student extends User implements Serializable {
    
    @NotEmpty
    private Long studentNumber;

    public Long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Long studentNumber) {
        this.studentNumber = studentNumber;
    }
}
