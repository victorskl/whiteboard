package whiteboard.domain.model;

import java.util.Date;

public abstract class Content {
    private String title;
    private User postedBy;
    private Date timeOfPost;
    private Date lastModify;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public Date getTimeOfPost() {
        return timeOfPost;
    }

    public void setTimeOfPost(Date timeOfPost) {
        this.timeOfPost = timeOfPost;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }
}
