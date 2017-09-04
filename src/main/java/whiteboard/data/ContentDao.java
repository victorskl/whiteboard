package whiteboard.data;

import whiteboard.domain.model.Announcement;

import java.util.List;

public interface ContentDao {
    List<Announcement> findAnnouncementsBySubjectCode(String subjectCode);
    void save(Announcement announcement);
    Announcement findById(Long id);
}
