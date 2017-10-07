package whiteboard.domain.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
    UserDetailsLmsUserImpl getUserDetailsLmsUserImpl();

    boolean hasRole(String role);
}
