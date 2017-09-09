package whiteboard.ui.converter;

import org.springframework.core.convert.converter.Converter;
import whiteboard.domain.model.SubjectContentType;

public class TMTypeConverter implements Converter<String, SubjectContentType> {

    @Override
    public SubjectContentType convert(String s) {
        if (s.equalsIgnoreCase(SubjectContentType.LECTURE.getShortName()))
            return SubjectContentType.LECTURE;

        else if (s.equalsIgnoreCase(SubjectContentType.WORKSHOP.getShortName()))
            return SubjectContentType.WORKSHOP;

        else if (s.equalsIgnoreCase(SubjectContentType.ASSESSMENT.getShortName()))
            return SubjectContentType.ASSESSMENT;

        return null;
    }
}
