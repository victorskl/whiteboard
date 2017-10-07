package whiteboard.domain.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whiteboard.data.UserDao;
import whiteboard.domain.model.Comment;
import whiteboard.domain.model.Role;
import whiteboard.domain.model.Staff;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Staff loadStaffByUsername(String username) {
        Staff staff = (Staff) userDao.findByUsername(username);
        if (staff == null) {
            throw new UsernameNotFoundException(username);
        }
        List<Role> roles = userDao.getRolesByUserId(staff.getId());
        staff.setRoles(roles);
        staff.setComments(userDao.getCommentsByStaffId(staff.getId()));
        return staff;
    }

    @Override
    public List getUsersByRoleId(Long roleId) {
        return userDao.getUsersByRoleId(roleId);
    }

    @Override
    public Long getRoleIdByRoleName(String roleName) {
        return userDao.getRoleIdByRoleName(roleName);
    }

    @Override
    public List<Comment> getCommentsByStaffId(Long staffId) {
        return userDao.getCommentsByStaffId(staffId);
    }

    @Override
    public void saveOrUpdateComment(Comment comment) {
        comment.resolveCommentState();
        userDao.saveOrUpdateComment(comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return userDao.getCommentById(id);
    }
}
