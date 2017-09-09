package whiteboard.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import whiteboard.domain.model.Subject;
import whiteboard.domain.service.ContentService;
import whiteboard.domain.service.SubjectService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

public class CommonController {

    @Autowired
    protected SubjectService subjectService;

    @Autowired
    protected ContentService contentService;

    @Autowired
    protected Validator validator;

    public void validate(Object entity, BindingResult result) {
        Set<ConstraintViolation<Object>> violations = validator.validate(entity);
        for (ConstraintViolation violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            //String msg = "Invalid " + propertyPath + "(" + message + ")";
            result.addError(new FieldError(entity.toString(), propertyPath, message));
        }
    }

    @ModelAttribute("subjectList")
    private List<Subject> subjectList() {
        return subjectService.getSubjects();
    }
}
