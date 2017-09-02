package whiteboard.domain.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "Announcements")
public class Announcement extends Content{

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
