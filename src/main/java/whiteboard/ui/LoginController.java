package whiteboard.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import whiteboard.ui.model.LoginFormDto;

@Controller
public class LoginController {

    /**
     * How login work?
     *
     * This LoginController just render the login form view only.
     *
     * The login processing delegate to /spring_security_login_check endpoint.
     * This endpoint is handled within Spring Security Framework.
     * Then, from that endpoint, it will process using authentication-provider(s).
     *
     * Then, the authentication provider is implemented database backend; in UserDetailsServiceImpl.
     * If user exist in database, the UserDetailsServiceImpl populate UserDetailsLmsUserImpl which is
     * an implementation of authorization provider pattern!
     *
     * All of these login processing sequences are defined at spring-security.xml.
     *
     */
    @GetMapping("/login")
    public String loginForm(ModelMap model) {
        logger.debug("loading login page");
        model.addAttribute("title", "Login - Whiteboard LMS");

        // this is only for UI model, DTO - data transfer object. this design pattern is
        // use for intermediary mediator pattern between domain model and UI model.
        // it has only UI validation logic; and state of the backing UI form that
        // might align with domain model to sudden extent.
        //
        // this object doesn't carry to backend for this instance.
        // Bec we delegate this task to Spring Security.
        model.addAttribute("loginFormDto", new LoginFormDto());

        return "login";
    }

    private static final Logger logger = LogManager.getLogger(LoginController.class);
}
