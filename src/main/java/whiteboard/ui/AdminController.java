package whiteboard.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import whiteboard.domain.model.Comment;
import whiteboard.domain.model.Staff;
import whiteboard.domain.model.User;
import whiteboard.ui.model.DefaultRoles;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/admin") // FIXME not working
public class AdminController extends CommonController {

    @GetMapping(value = {"/admin", "/admin/dashboard"})
    public String dashboard(ModelMap model) {
        logger.info("Admin Dashboard");
        model.addAttribute("title", "Admin Dashboard");

        Long lecturerRoleId = userService.getRoleIdByRoleName(DefaultRoles.ROLE_LECTURER.toString());
        List<Staff> lecturers = userService.getUsersByRoleId(lecturerRoleId);
        for (Staff staff : lecturers) {
            staff.setComments(userService.getCommentsByStaffId(staff.getId()));
        }

        model.addAttribute("lectures", lecturers);

        return "admin/dashboard";
    }

    @GetMapping(value = {"/admin/{username}/comments"})
    public String commentView(ModelMap model, @PathVariable String username) {
        model.addAttribute("title", "Comments on " + username);
        model.addAttribute("username", username);

        Staff lecturer = userService.loadStaffByUsername(username);
        model.addAttribute("lecturer", lecturer);

        return "admin/comments";
    }

    @GetMapping(value = {"/admin/{username}/comments/add"})
    public String commentFormView(ModelMap model, @PathVariable String username) {
        model.addAttribute("title", "Add Comment on " + username);
        model.addAttribute("username", username);

        Comment comment = new Comment();

        model.addAttribute("comment", comment);

        return "admin/commentform";
    }

    @PostMapping(value = {"/admin/{username}/comments/add"})
    public String commentPost(@ModelAttribute("comment") @Valid Comment comment,
                              BindingResult result, @PathVariable String username) {

        validate(comment, result);

        if (result.hasErrors()) {
            logger.error("hasErrors");
            return "admin/commentform";
        }

        if (comment.getLecturer() == null) {
            comment.setLecturer(userService.loadStaffByUsername(username));
        }

        if (comment.getLocked()) comment.setLocked(false); // release the lock

        userService.saveOrUpdateComment(comment);

        return "redirect:/admin/"+username+"/comments";
    }

    @GetMapping(value = {"/admin/{username}/comments/{id}/edit"})
    public String commentFormEditView(ModelMap model, @PathVariable String username, @PathVariable Long id) {
        model.addAttribute("title", "Edit Comment on " + username);
        model.addAttribute("username", username);

        User currentLoginUser = authenticationFacade.getUserDetailsLmsUserImpl().getUser();

        Comment comment = userService.getCommentById(id);

        if (comment.getLocked()
                && !comment.getLockedBy().getUsername().equalsIgnoreCase(currentLoginUser.getUsername())) {

            logger.info("Comment ID: " + comment.getId() + " is locked. Redirecting..");
            return "redirect:/admin/" + username + "/comments";
        }

        else if (comment.getLockedBy() != null
                && comment.getLockedBy().getUsername().equalsIgnoreCase(currentLoginUser.getUsername())) {
            model.addAttribute("comment", comment);
        }

        else {
            comment.setLocked(true);
            comment.setLockedBy(currentLoginUser);
            userService.saveOrUpdateComment(comment);
            model.addAttribute("comment", comment);
        }

        return "admin/commentform";
    }


    private static final Logger logger = LogManager.getLogger(AdminController.class);
}
