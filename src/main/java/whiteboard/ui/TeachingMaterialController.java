package whiteboard.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import whiteboard.domain.model.Subject;
import whiteboard.domain.model.SubjectContentType;
import whiteboard.domain.model.TeachingMaterial;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/tm")
public class TeachingMaterialController extends CommonController {

    @GetMapping(value = {"/new", "/add", "/"})
    public String tmForm(ModelMap model, @RequestParam(required = false) String subjectCode) {
        model.addAttribute("title", "Add Teaching Material");

        TeachingMaterial tm = new TeachingMaterial();

        logger.info(subjectCode);

        if (subjectCode != null && !subjectCode.isEmpty()) {
            Subject subject = subjectService.findBySubjectCode(subjectCode);
            tm.setSubject(subject);
        }

        model.addAttribute("tm", tm);
        model.addAttribute("tmTypes", Arrays.asList(SubjectContentType.values()));

        return "tmform";
    }

    @PostMapping("/add")
    public String addTm(@ModelAttribute("tm") @Valid TeachingMaterial tm, BindingResult result, ModelMap model) {

        validate(tm, result);

        if (result.hasErrors()) {
            logger.error("hasErrors");
            model.addAttribute("tmTypes", Arrays.asList(SubjectContentType.values()));
            return "tmform";
        }

        contentService.saveOrUpdateTeachingMaterial(tm);

        String view = "redirect:/subject/"+tm.getSubject().getCode();
        if (tm.getId() != null)
            view = "redirect:/tm/"+tm.getId();

        return view;
    }

    @GetMapping("/{id}")
    public String tmView(@PathVariable Long id, ModelMap model) {
        TeachingMaterial tm = contentService.findTeachingMaterialById(id);
        model.addAttribute("title", tm.getTitle());
        model.addAttribute("tm", tm);
        return "tm";
    }

    @GetMapping("/{id}/update")
    public String tmUpdate(@PathVariable Long id, ModelMap model) {
        TeachingMaterial tm = contentService.findTeachingMaterialById(id);
        model.addAttribute("title", tm.getTitle());
        model.addAttribute("tm", tm);
        model.addAttribute("tmTypes", Arrays.asList(SubjectContentType.values()));
        return "tmform";
    }

    @PostMapping("/{id}/delete")
    public String tmDelete(@PathVariable Long id, ModelMap model) {
        TeachingMaterial tm = contentService.findTeachingMaterialById(id);
        contentService.deleteTeachingMaterialById(id);
        return "redirect:/subject/"+ tm.getSubject().getCode()
                + "?deletedTeachingMaterial=" + tm.getTitle();
    }

/*
    @ModelAttribute("tmTypes")
    public List<SubjectContentType> types() {
        return Arrays.asList(SubjectContentType.values());
    }
*/

    private static final Logger logger = LogManager.getLogger(TeachingMaterialController.class);
}
