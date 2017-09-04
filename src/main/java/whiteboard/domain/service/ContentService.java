package whiteboard.domain.service;

import whiteboard.domain.model.Announcement;

import java.util.List;

public interface ContentService {
    List<Announcement> findAnnouncementsBySubjectCode(String subjectCode);
    void addAnnouncement(Announcement announcement);
    Announcement findAnnouncementById(Long id);
}
