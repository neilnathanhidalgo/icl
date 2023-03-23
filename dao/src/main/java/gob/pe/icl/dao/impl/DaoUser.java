
package gob.pe.icl.dao.impl;

import com.jofrantoba.model.jpa.daoentity.AbstractJpaDao;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoUser extends AbstractJpaDao<User>
        implements InterDaoUser {

    public DaoUser(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        super();
        setClazz(User.class);
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<User> findAllUsers() {
        return getCurrentSession()
                .createQuery("SELECT c FROM User c", User.class)
                .getResultList();
    }
}

