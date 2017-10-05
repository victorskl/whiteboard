package whiteboard.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Staffs")
public class Staff extends User implements Serializable {

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
