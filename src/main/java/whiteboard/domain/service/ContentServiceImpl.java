package whiteboard.domain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whiteboard.data.ContentDao;
import whiteboard.domain.model.Announcement;
import whiteboard.domain.model.TeachingMaterial;

import javax.transaction.Transactional;
import java.util.List;

@Service("contentService")
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    public List<Announcement> findAnnouncementsBySubjectCode(String code) {
        return contentDao.findAnnouncementsBySubjectCode(code);
    }

    @Override
    public Announcement findAnnouncementById(Long id) {
        return contentDao.findAnnouncementById(id);
    }

    @Override
    public void deleteAnnouncementById(Long id) {
        contentDao.deleteAnnouncementById(id);
    }

    @Override
    public void saveOrUpdateAnnouncement(Announcement announcement) {
        announcement.resolveContentState();
        contentDao.saveOrUpdateAnnouncement(announcement);
    }

    @Override
    public void saveOrUpdateTeachingMaterial(TeachingMaterial tm) {
        tm.resolveContentState();
        contentDao.saveOrUpdateTeachingMaterial(tm);
    }

    @Override
    public TeachingMaterial findTeachingMaterialById(Long id) {
        return contentDao.findTeachingMaterialById(id);
    }

    @Override
    public void deleteTeachingMaterialById(Long id) {
        contentDao.deleteTeachingMaterialById(id);
    }

    @Override
    public List<TeachingMaterial> findLectureNotesBySubjectCode(String subjectCode) {
        return contentDao.findLectureNotesBySubjectCode(subjectCode);
    }

    @Override
    public List<TeachingMaterial> findAssessmentNotesBySubjectCode(String subjectCode) {
        return contentDao.findAssessmentNotesBySubjectCode(subjectCode);
    }

    @Override
    public List<TeachingMaterial> findWorkshopNotesBySubjectCode(String subjectCode) {
        return contentDao.findWorkshopNotesBySubjectCode(subjectCode);
    }

    private static final Logger logger = LogManager.getLogger(ContentServiceImpl.class);
}
