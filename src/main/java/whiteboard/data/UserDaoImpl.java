package whiteboard.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import whiteboard.domain.model.User;

import javax.persistence.NoResultException;

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

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
}
