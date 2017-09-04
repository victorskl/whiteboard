package whiteboard.data;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import whiteboard.domain.model.Subject;

import java.util.List;

@Repository("subjectDao")
public class SubjectDaoImpl extends AbstractDao implements SubjectDao {

    @Override
    public List<Subject> getSubjects() {
        Query query = getSession().createQuery("from Subjects");
        List<Subject> subjectList = query.list();
        return subjectList;
    }

    @Override
    public void save(Subject subject) {
        persist(subject);
    }

    @Override
    public Subject findBySubjectCode(String code) {
        Query<Subject> query = getSession()
                .createQuery("select s from Subjects as s where s.code = :code")
                .setParameter("code", code);
        return query.getSingleResult();
    }
}
