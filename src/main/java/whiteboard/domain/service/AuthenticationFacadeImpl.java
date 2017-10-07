package whiteboard.domain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Just a convenience facade for the static SecurityContextHolder.getContext()
 *
 */
@Component("authenticationFacade")
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UserDetailsLmsUserImpl getUserDetailsLmsUserImpl() {
        return (UserDetailsLmsUserImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    @Override
    public boolean hasRole(String role) {
        logger.info("checking role: " + role);
        return getUserDetailsLmsUserImpl().getAuthorities()
                .contains(new SimpleGrantedAuthority(role));
    }

    private static final Logger logger = LogManager.getLogger(AuthenticationFacadeImpl.class);
}
