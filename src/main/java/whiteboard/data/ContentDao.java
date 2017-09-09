package whiteboard.data;

import whiteboard.domain.model.Announcement;
import whiteboard.domain.model.TeachingMaterial;

import java.util.List;

public interface ContentDao {
    List<Announcement> findAnnouncementsBySubjectCode(String subjectCode);
    Announcement findAnnouncementById(Long id);
    void deleteAnnouncementById(Long id);
    void saveOrUpdateAnnouncement(Announcement announcement);
    List<TeachingMaterial> findLectureNotesBySubjectCode(String subjectCode);
    void saveOrUpdateTeachingMaterial(TeachingMaterial tm);
    TeachingMaterial findTeachingMaterialById(Long id);
    void deleteTeachingMaterialById(Long id);
    List<TeachingMaterial> findWorkshopNotesBySubjectCode(String subjectCode);
    List<TeachingMaterial> findAssessmentNotesBySubjectCode(String subjectCode);
}
