package whiteboard.domain.service;

import whiteboard.domain.model.Comment;
import whiteboard.domain.model.Staff;

import java.util.List;

public interface UserService {
    Staff loadStaffByUsername(String username);

    List getUsersByRoleId(Long roleId);
    Long getRoleIdByRoleName(String roleName);

    List<Comment> getCommentsByStaffId(Long staffId);

    void saveOrUpdateComment(Comment comment);

    Comment getCommentById(Long id);
}
