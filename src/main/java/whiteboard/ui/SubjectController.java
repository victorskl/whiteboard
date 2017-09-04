package whiteboard.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import whiteboard.domain.model.Announcement;
import whiteboard.domain.model.Subject;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/subject")
public class SubjectController extends CommonController {

    @GetMapping("/list")
    public String subjects(ModelMap model) {
        model.addAttribute("title", "Subject List");
        model.addAttribute("subjects", subjectService.getSubjects());
        return "subjectlist";
    }

    @GetMapping(value = {"/new", "/add"})
    public String newSubjectForm(ModelMap model) {
        model.addAttribute("title", "Add New Subject");
        model.addAttribute("subject", new Subject());
        return "subjectnew";
    }

    @PostMapping("/add")
    public String addSubject(@ModelAttribute("subject") @Valid Subject subject, BindingResult result) {
        Set<ConstraintViolation<Subject>> violations = validator.validate(subject);
        for (ConstraintViolation<Subject> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            //String msg = "Invalid " + propertyPath + "(" + message + ")";
            result.addError(new FieldError("subject", propertyPath, message));
        }

        if (result.hasErrors()) {
            return "subjectnew";
        }

        subjectService.addSubject(subject);

        return "redirect:/subject/list";
    }

    @GetMapping("/{subjectCode}")
    public String subjectView(@PathVariable String subjectCode, ModelMap model) {
        Subject subject = subjectService.findBySubjectCode(subjectCode);
        model.addAttribute("title", subjectCode);
        model.addAttribute("subject", subject);
        //logger.info("size: " + subject.getContents().size());
        // will throw error https://www.google.com.au/search?q=failed+to+lazily+initialize+a+collection+of+role
        // move to service layer for Transactional scope https://stackoverflow.com/a/42206232

        List<Announcement> announcementList = contentService.findAnnouncementsBySubjectCode(subjectCode);
        model.addAttribute("announcements", announcementList);
        return "subject";
    }

    private static final Logger logger = LogManager.getLogger(SubjectController.class);
}
