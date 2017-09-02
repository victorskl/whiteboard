package whiteboard.domain.model;

import java.io.File;

public class TeachingMaterial extends Content{
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
