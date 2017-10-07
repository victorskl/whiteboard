package whiteboard.domain.model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "Students")
public class Student extends User implements Serializable {
    
    private Long studentNumber;

    public Long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Long studentNumber) {
        this.studentNumber = studentNumber;
    }
}
