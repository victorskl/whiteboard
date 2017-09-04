package whiteboard.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import whiteboard.domain.model.Announcement;
import whiteboard.domain.model.Subject;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends CommonController {

    @GetMapping(value = {"/new", "/add"})
    public String newAnnouncementForm(ModelMap model, @RequestParam(required = false) String subjectCode) {
        model.addAttribute("title", "Add New Announcement");
        Announcement announcement = new Announcement();

        logger.info(subjectCode);

        if (subjectCode != null && !subjectCode.isEmpty()) {
            Subject subject = subjectService.findBySubjectCode(subjectCode);
            announcement.setSubject(subject);
        }

        model.addAttribute("announcement", announcement);

        return "announcenew";
    }

    @PostMapping("/add")
    public String addAnnouncement(@ModelAttribute("announcement") @Valid Announcement announcement, BindingResult result) {

        validate(announcement, result);

        if (result.hasErrors()) {
            System.out.println("hasErrors");
            return "announcenew";
        }

        Date now = new Date();
        announcement.setTimeOfPost(now);
        announcement.setLastModified(now);

        announcement.setPostedBy(null);

        contentService.addAnnouncement(announcement);

        return "redirect:/subject/"+announcement.getSubject().getCode();
    }

    @GetMapping("/{id}")
    public String subjectView(@PathVariable Long id, ModelMap model) {
        Announcement announcement = contentService.findAnnouncementById(id);
        model.addAttribute("title", announcement.getTitle());
        model.addAttribute("announcement", announcement);
        return "announcement";
    }

    @ModelAttribute("subjectList")
    private List<Subject> subjectList() {
        return subjectService.getSubjects();
    }

    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);
}
