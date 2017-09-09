package whiteboard.data;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import whiteboard.domain.model.Subject;

import java.util.List;

@Repository("subjectDao")
public class SubjectDaoImpl extends AbstractDao implements SubjectDao {

    @Override
    public List<Subject> getSubjects() {
        Query query = getSession().createQuery("select s from Subjects s");
        List<Subject> subjectList = query.list();
        return subjectList;
    }

    @Override
    public void save(Subject subject) {
        doPersist(subject);
    }

    public void saveOrUpdate(Subject subject) {
        doSaveOrUpdate(subject);
    }

    @Override
    public Subject findBySubjectCode(String subjectCode) {
        Query<Subject> query = getSession()
                .createQuery("select s from Subjects as s where s.code = :code")
                .setParameter("code", subjectCode);
        return query.getSingleResult();
    }

    @Override
    public void deleteBySubjectCode(String subjectCode) {
        doDelete(findBySubjectCode(subjectCode));
    }
}
