package whiteboard.data;

import whiteboard.domain.model.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> getSubjects();
    void save(Subject subject);
    void saveOrUpdate(Subject subject);
    Subject findBySubjectCode(String subjectCode);
    void deleteBySubjectCode(String subjectCode);
}
