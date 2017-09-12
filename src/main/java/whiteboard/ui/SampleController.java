package whiteboard.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping("/cover")
    public String cover(ModelMap model) {
        model.addAttribute("title", "Cover Page - Whiteboard LMS");
        logger.debug("loading cover page");
        return "cover";
    }

    @RequestMapping("/architecture")
    public String architecture(ModelMap model) {
        model.addAttribute("title", "About Architecture - Whiteboard LMS");
        logger.debug("loading architecture page");
        return "architecture";
    }

    @RequestMapping("/theme")
    public String theme(ModelMap model) {
        model.addAttribute("title", "Theme Page - Bootstrap");
        return "theme";
    }

    @RequestMapping(value = {"/admin", "/admin/dashboard"})
    public String dashboard(ModelMap model) {
        model.addAttribute("title", "Dashboard - Bootstrap");
        return "admin/dashboard";
    }

    private static final Logger logger = LogManager.getLogger(SampleController.class);
}
