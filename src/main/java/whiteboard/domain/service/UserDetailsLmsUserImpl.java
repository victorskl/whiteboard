package whiteboard.domain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import whiteboard.domain.model.Role;
import whiteboard.domain.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsLmsUserImpl implements UserDetails {

    private User user;

    public UserDetailsLmsUserImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            logger.info("Added authority: " + role.getName());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        logger.info("isAccountNonExpired: " + user.getEnabled());
        return user.getEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        logger.info("isAccountNonLocked: " + user.getEnabled());
        return user.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        logger.info("isCredentialsNonExpired: " + user.getEnabled());
        return user.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        logger.info("isEnabled: " + user.getEnabled());
        return user.getEnabled();
    }

    private static final Logger logger = LogManager.getLogger(UserDetailsLmsUserImpl.class);
}
