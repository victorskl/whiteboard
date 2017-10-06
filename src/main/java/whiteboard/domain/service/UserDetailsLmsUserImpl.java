package whiteboard.domain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import whiteboard.domain.model.User;

import java.util.Collection;

public class UserDetailsLmsUserImpl implements UserDetails {

    private User user;

    public UserDetailsLmsUserImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (final Privilege privilege : user.getPrivileges()) {
//            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
//        }
//        return authorities;
        return null;
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
