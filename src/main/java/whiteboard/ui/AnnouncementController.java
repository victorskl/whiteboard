package whiteboard.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import whiteboard.domain.model.Announcement;
import whiteboard.domain.model.Subject;
import whiteboard.ui.model.DefaultRoles;

import javax.validation.Valid;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends CommonController {

    @GetMapping(value = {"/new", "/add", "/"})
    public String newAnnouncementForm(ModelMap model, @RequestParam(required = false) String subjectCode) {
        model.addAttribute("title", "Add New Announcement");
        Announcement announcement = new Announcement();

        logger.info(subjectCode);

        if (subjectCode != null && !subjectCode.isEmpty()) {
            Subject subject = subjectService.findBySubjectCode(subjectCode);
            announcement.setSubject(subject);
        }

        model.addAttribute("announcement", announcement);

        return "announcementform";
    }

    @PostMapping("/add")
    public String addAnnouncement(@ModelAttribute("announcement") @Valid Announcement announcement, BindingResult result) {

        validate(announcement, result);

        if (result.hasErrors()) {
            logger.error("hasErrors");
            return "announcementform";
        }

        contentService.saveOrUpdateAnnouncement(announcement);

        String view = "redirect:/subject/"+announcement.getSubject().getCode();
        if (announcement.getId() != null)
            view = "redirect:/announcement/"+announcement.getId();
        return view;
    }

    /**
     * Lecturers can only look at the announcements that they have created.
     */
    private boolean featureBLecturerAnnouncementConstraint(Announcement announcement) {
        if (authenticationFacade.hasRole(DefaultRoles.ROLE_LECTURER.toString())) {
            return !announcement.getPostedBy().getUsername()
                    .equalsIgnoreCase(authenticationFacade.getUserDetailsLmsUserImpl().getUsername());
        }
        return false;
    }

    @GetMapping("/{id}")
    public String announcementView(@PathVariable Long id, ModelMap model) {
        Announcement announcement = contentService.findAnnouncementById(id);
        if (featureBLecturerAnnouncementConstraint(announcement)) {
            // throw new ForbiddenException();
            return "announcement";
        }
        model.addAttribute("title", announcement.getTitle());
        model.addAttribute("announcement", announcement);
        return "announcement";
    }

    @GetMapping("/{id}/update")
    public String announcementUpdate(@PathVariable Long id, ModelMap model) {
        Announcement announcement = contentService.findAnnouncementById(id);
        if (featureBLecturerAnnouncementConstraint(announcement)) {
            // throw new ForbiddenException();
            return "announcement";
        }
        model.addAttribute("title", announcement.getTitle());
        model.addAttribute("announcement", announcement);
        return "announcementform";
    }

    @PostMapping("/{id}/delete")
    public String announcementDelete(@PathVariable Long id, ModelMap model) {
        Announcement announcement = contentService.findAnnouncementById(id);
        if (featureBLecturerAnnouncementConstraint(announcement)) {
            // throw new ForbiddenException();
            return "announcement";
        }
        contentService.deleteAnnouncementById(id);
        return "redirect:/subject/"+ announcement.getSubject().getCode()
                + "?deletedAnnouncement=" + announcement.getTitle();
    }

    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);
}
