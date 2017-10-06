package whiteboard.data;

import whiteboard.domain.model.User;

public interface UserDao {

    /**
     * Assumption: a username is an unique identifier of a User of the system.
     *
     * @param username
     * @return
     */
    User findByUsername(String username);
}
