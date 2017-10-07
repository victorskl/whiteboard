package whiteboard.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import whiteboard.domain.model.Comment;
import whiteboard.domain.model.Role;
import whiteboard.domain.model.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public User findByUsername(String username) {
        try {
            Query<User> query = getSession()
                    .createQuery("select u from Users as u where u.username = :username")
                    .setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("No user found.");
            return null;
        }
    }

    @Override
    public List getUsersByRoleId(Long id) {
        Query query = getSession()
                .createQuery("select u from Users as u inner join u.roles as r where r.id = :id")
                .setParameter("id", id);
        return query.list();
    }


    // Role related data access; can be in separate Dao

    @Override
    public List<Role> getRolesByUserId(Long id) {
        Query query = getSession()
                .createQuery("select r from Roles as r inner join r.users as u where u.id = :id")
                .setParameter("id", id);
        List<Role> roles = query.list();
        return roles;
    }

    @Override
    public Long getRoleIdByRoleName(String roleName) {
        try {
            Query query = getSession()
                    .createQuery("select r.id from Roles as r where r.name = :roleName")
                    .setParameter("roleName", roleName);
            return (Long) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("No role found with name: " + roleName);
            return null;
        }
    }


    // Comment related data access; can be in separate Dao

    @Override
    public List<Comment> getCommentsByStaffId(Long id) {
        Query query = getSession()
                .createQuery("select c from Comments as c inner join c.lecturer as l where l.id = :id")
                .setParameter("id", id);
        return query.list();
    }

    @Override
    public void saveOrUpdateComment(Comment comment) {
        doSaveOrUpdate(comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        Query query = getSession()
                .createQuery("select c from Comments as c where c.id = :id")
                .setParameter("id", id);
        return (Comment) query.getSingleResult();
    }

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
}
