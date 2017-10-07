package whiteboard.data;

import whiteboard.domain.model.Comment;
import whiteboard.domain.model.Role;
import whiteboard.domain.model.User;

import java.util.List;

public interface UserDao {

    /**
     * Assumption: a username is an unique identifier of a User of the system.
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    List<Role> getRolesByUserId(Long id);

    List getUsersByRoleId(Long id);

    Long getRoleIdByRoleName(String roleName);

    List<Comment> getCommentsByStaffId(Long id);

    void saveOrUpdateComment(Comment comment);

    Comment getCommentById(Long id);
}
