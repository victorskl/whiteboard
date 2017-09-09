package whiteboard.domain.service;

import whiteboard.domain.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getSubjects();
    void addSubject(Subject subject);
    void saveOrUpdateSubject(Subject subject);
    Subject findBySubjectCode(String code);
    void deleteBySubjectCode(String subjectCode);
}
