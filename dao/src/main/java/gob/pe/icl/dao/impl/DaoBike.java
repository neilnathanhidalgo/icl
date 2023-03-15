package gob.pe.icl.dao.impl;

import com.jofrantoba.model.jpa.daoentity.AbstractJpaDao;
import gob.pe.icl.dao.inter.InterDaoBike;
import gob.pe.icl.entity.Bike;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoBike extends AbstractJpaDao<Bike>
        implements InterDaoBike {

    public DaoBike(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super();
        setClazz(Bike.class);
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Bike> findByUserId(Long userId) {
        return getCurrentSession()
                .createQuery("SELECT c FROM Bike c WHERE c.userId = :userId", Bike.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    @Override
    public List<Bike> findAll() {
        return getCurrentSession()
                .createQuery("SELECT c FROM Bike c", Bike.class)
                .getResultList();
    }


}
