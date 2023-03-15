package gob.pe.icl.dao.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoCar;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import org.hibernate.Transaction;
import org.junit.Test;

public class TestDaoCarInsert extends TestBaseDao{

    @Test
    public void createCar1() throws UnknownException {
        Car entity = contextEntity.getBean(Car.class);
        InterDaoCar dao = contextDao.getBean(DaoCar.class);
        entity.setBrand("XYZ");
        entity.setModel("123");
        Transaction tx=dao.getSession().beginTransaction();
        dao.save(entity);
        tx.commit();
    }
}
