package whiteboard.data;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import whiteboard.domain.model.Announcement;
import whiteboard.domain.model.SubjectContentType;
import whiteboard.domain.model.TeachingMaterial;

import java.util.List;

@Repository("contentDao")
public class ContentDaoImpl extends AbstractDao implements ContentDao {

    @Override
    public List<Announcement> findAnnouncementsBySubjectCode(String subjectCode) {
        Query query = getSession()
                .createQuery("select a from Announcements as a where a.subject.code = :code order by a.timeOfPost desc")
                .setParameter("code", subjectCode);
        return query.list();
    }

    @Override
    public Announcement findAnnouncementById(Long id) {
        Query query = getSession()
                .createQuery("select a from Announcements as a where a.id = :id")
                .setParameter("id", id);
        return (Announcement) query.getSingleResult();
    }

    @Override
    public void deleteAnnouncementById(Long id) {
        doDelete(findAnnouncementById(id));
    }

    @Override
    public void saveOrUpdateAnnouncement(Announcement announcement) {
        doSaveOrUpdate(announcement);
    }

    @Override
    public void saveOrUpdateTeachingMaterial(TeachingMaterial tm) {
        doSaveOrUpdate(tm);
    }

    @Override
    public TeachingMaterial findTeachingMaterialById(Long id) {
        Query query = getSession()
                .createQuery("select tm from TeachingMaterials as tm where tm.id = :id")
                .setParameter("id", id);
        return (TeachingMaterial) query.getSingleResult();
    }

    @Override
    public void deleteTeachingMaterialById(Long id) {
        doDelete(findTeachingMaterialById(id));
    }

    @Override
    public List<TeachingMaterial> findLectureNotesBySubjectCode(String subjectCode) {
        Query query = getSession()
                .createQuery("select tm from TeachingMaterials as tm where tm.type = :type and tm.subject.code = :code")
                .setParameter("type", SubjectContentType.LECTURE)
                .setParameter("code", subjectCode);
        return query.list();
    }

    @Override
    public List<TeachingMaterial> findWorkshopNotesBySubjectCode(String subjectCode) {
        Query query = getSession()
                .createQuery("select tm from TeachingMaterials as tm where tm.type = :type and tm.subject.code = :code")
                .setParameter("type", SubjectContentType.WORKSHOP)
                .setParameter("code", subjectCode);
        return query.list();
    }

    @Override
    public List<TeachingMaterial> findAssessmentNotesBySubjectCode(String subjectCode) {
        Query query = getSession()
                .createQuery("select tm from TeachingMaterials as tm where tm.type = :type and tm.subject.code = :code")
                .setParameter("type", SubjectContentType.ASSESSMENT)
                .setParameter("code", subjectCode);
        return query.list();
    }
}
