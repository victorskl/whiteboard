package whiteboard.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "AssessmentSubmissionLinks")
public class AssessmentSubmission extends Content{

    private Date dueTime;
    private String linkURL;
    private Integer submissionCount;

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public Integer getSubmissionCount() {
        return submissionCount;
    }

    public void setSubmissionCount(Integer submissionCount) {
        this.submissionCount = submissionCount;
    }
}
