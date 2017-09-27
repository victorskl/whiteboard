package whiteboard.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.Serializable;
import java.util.List;

@Entity(name = "TeachingMaterials")
public class TeachingMaterial extends Content implements Serializable {

    private List<String> attachments;

    @NotEmpty
    @Column(length = 4000)
    private String description;

    @NotNull
    private SubjectContentType type;

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
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
