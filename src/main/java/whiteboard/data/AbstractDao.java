package whiteboard.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void doPersist(Object entity) {
        getSession().persist(entity);
    }

    public void doDelete(Object entity) {
        getSession().delete(entity);
    }

    public void doSaveOrUpdate(Object entity) {
        getSession().saveOrUpdate(entity);
    }
}