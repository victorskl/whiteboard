package whiteboard.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import whiteboard.domain.model.Announcement;
import whiteboard.domain.model.Subject;
import whiteboard.domain.model.TeachingMaterial;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController extends CommonController {

    @GetMapping(value = {"/list", "/"})
    public String subjects(ModelMap model,@RequestParam(required = false) String deletedSubjectCode) {
        model.addAttribute("title", "Subject List");
        model.addAttribute("subjects", subjectService.getSubjects());
        model.addAttribute("deletedSubjectCode", deletedSubjectCode);
        return "subjectlist";
    }

    @GetMapping(value = {"/new", "/add"})
    public String newSubjectForm(ModelMap model) {
        model.addAttribute("title", "Add New Subject");
        model.addAttribute("subject", new Subject());
        return "subjectform";
    }

    @PostMapping("/add")
    public String addSubject(@ModelAttribute("subject") @Valid Subject subject, BindingResult result) {

        validate(subject, result);

        if (result.hasErrors()) {
            return "subjectform";
        }

        subjectService.saveOrUpdateSubject(subject);

        return "redirect:/subject/list";
    }

    @GetMapping("/{subjectCode}")
    public String subjectView(@PathVariable String subjectCode, ModelMap model
            , @RequestParam(required = false) String deletedAnnouncement
            , @RequestParam(required = false) String deletedTeachingMaterial) {
        Subject subject = subjectService.findBySubjectCode(subjectCode);
        model.addAttribute("title", subjectCode);
        model.addAttribute("subject", subject);
        model.addAttribute("deletedAnnouncement", deletedAnnouncement);
        model.addAttribute("deletedTeachingMaterial", deletedTeachingMaterial);

        //logger.info("size: " + subject.getContents().size());
        // will throw error https://www.google.com.au/search?q=failed+to+lazily+initialize+a+collection+of+role
        // move to service layer for Transactional scope https://stackoverflow.com/a/42206232

        List<Announcement> announcementList = contentService.findAnnouncementsBySubjectCode(subjectCode);
        model.addAttribute("announcements", announcementList);

        List<TeachingMaterial> lectureNotes = contentService.findLectureNotesBySubjectCode(subjectCode);
        model.addAttribute("lectureNotes", lectureNotes);

        List<TeachingMaterial> workshopNotes = contentService.findWorkshopNotesBySubjectCode(subjectCode);
        model.addAttribute("workshopNotes", workshopNotes);

        List<TeachingMaterial> assessmentNotes = contentService.findAssessmentNotesBySubjectCode(subjectCode);
        model.addAttribute("assessmentNotes", assessmentNotes);

        return "subject";
    }

    @GetMapping("/{subjectCode}/update")
    public String subjectUpdate(@PathVariable String subjectCode, ModelMap model) {
        Subject subject = subjectService.findBySubjectCode(subjectCode);
        model.addAttribute("title", subjectCode);
        model.addAttribute("subject", subject);
        return "subjectform";
    }

    @PostMapping("/{subjectCode}/delete")
    public String subjectDelete(@PathVariable String subjectCode, ModelMap model) {
        subjectService.deleteBySubjectCode(subjectCode);
        return "redirect:/subject/list?deletedSubjectCode=" + subjectCode;
    }

    private static final Logger logger = LogManager.getLogger(SubjectController.class);
}
