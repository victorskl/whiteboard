package whiteboard.domain.model;

import javax.persistence.Entity;
import java.io.File;
import java.io.Serializable;

@Entity(name = "TeachingMaterials")
public class TeachingMaterial extends Content implements Serializable {

    private File attachment;
    private String description;
    private SubjectContentType type;

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubjectContentType getType() {
        return type;
    }

    public void setType(SubjectContentType type) {
        this.type = type;
    }
}
