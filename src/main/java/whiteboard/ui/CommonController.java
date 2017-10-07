package whiteboard.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import whiteboard.domain.model.Subject;
import whiteboard.domain.service.AuthenticationFacade;
import whiteboard.domain.service.ContentService;
import whiteboard.domain.service.SubjectService;
import whiteboard.domain.service.UserService;

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

    @Autowired
    protected AuthenticationFacade authenticationFacade;

    @Autowired
    protected UserService userService;

    public void validate(Object entity, BindingResult result) {
        Set<ConstraintViolation<Object>> violations = validator.validate(entity);
        for (ConstraintViolation violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            //String msg = "Invalid " + propertyPath + "(" + message + ")";
            result.addError(new FieldError(entity.toString(), propertyPath, message));
        }

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors ) {
                String message = error.getObjectName() + " - " + error.getDefaultMessage();
                logger.error(message);
                System.err.println(message);
            }
        }
    }

    @ModelAttribute("subjectList")
    private List<Subject> subjectList() {
        return subjectService.getSubjects();
    }

    private static final Logger logger = LogManager.getLogger(CommonController.class);

}

@ResponseStatus(HttpStatus.FORBIDDEN)
class ForbiddenException extends RuntimeException {}