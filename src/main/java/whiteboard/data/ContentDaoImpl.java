package whiteboard.data;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import whiteboard.domain.model.Announcement;

import java.util.List;

@Repository("contentDao")
public class ContentDaoImpl extends AbstractDao implements ContentDao {

    @Override
    public List<Announcement> findAnnouncementsBySubjectCode(String subjectCode) {
        Query query = getSession()
                .createQuery("select a from Announcements as a where subject_code = :code")
                .setParameter("code", subjectCode);
        return query.list();
    }

    @Override
    public void save(Announcement announcement) {
        persist(announcement);
    }

    @Override
    public Announcement findById(Long id) {
        Query<Announcement> query = getSession()
                .createQuery("select a from Announcements as a where id = :id")
                .setParameter("id", id);
        return query.getSingleResult();
    }
}
