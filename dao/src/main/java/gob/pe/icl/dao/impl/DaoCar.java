package gob.pe.icl.dao.impl;

import com.jofrantoba.model.jpa.daoentity.AbstractJpaDao;
import gob.pe.icl.dao.inter.InterDaoCar;
import gob.pe.icl.entity.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DaoCar extends AbstractJpaDao<Car>
        implements InterDaoCar {

    public DaoCar(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super();
        setClazz(Car.class);
        this.setSessionFactory(sessionFactory);
    }
    @Override
    public List<Car> findAll() {   
        return getCurrentSession()
                .createQuery("SELECT c FROM Car c", Car.class)
                .getResultList();
    }
}

