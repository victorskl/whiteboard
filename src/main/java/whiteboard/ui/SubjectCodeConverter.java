package whiteboard.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import whiteboard.domain.model.Subject;
import whiteboard.domain.service.SubjectService;

public class SubjectCodeConverter implements Converter<String, Subject> {

    @Autowired
    private SubjectService subjectService;

    @Override
    public Subject convert(String s) {
        return subjectService.findBySubjectCode(s);
    }
}
