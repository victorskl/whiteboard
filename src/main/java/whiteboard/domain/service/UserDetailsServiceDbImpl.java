package whiteboard.domain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whiteboard.data.UserDao;
import whiteboard.domain.model.Role;
import whiteboard.domain.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceDbImpl implements UserDetailsService {

    private UserDao userDao;

    public UserDetailsServiceDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<Role> roles = userDao.getRolesByUserId(user.getId());
        user.setRoles(roles);
        logger.info("User has login: " + user.getUsername());
        return new UserDetailsLmsUserImpl(user);
    }

    private static final Logger logger = LogManager.getLogger(UserDetailsServiceDbImpl.class);
}
