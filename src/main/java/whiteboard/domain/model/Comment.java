package whiteboard.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "comments")
public class Comment {

    @Id
    private Long id;

    @NotEmpty
    @ManyToOne
    private Staff lecturer;

    @NotEmpty
    private String message;

    private Boolean locked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Staff getLecturer() {
        return lecturer;
    }

    public void setLecturer(Staff lecturer) {
        this.lecturer = lecturer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
