package gob.pe.icl.dao.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoBike;
import gob.pe.icl.entity.Bike;
import org.hibernate.Transaction;
import org.junit.Test;

public class TestDaoBikeInsert extends TestBaseDao{
    @Test
    public void createEntity1() throws UnknownException {
        Bike entity = contextEntity.getBean(Bike.class);
        InterDaoBike dao = contextDao.getBean(DaoBike.class);
        entity.setBrand("XYZ");
        entity.setModel("123");
        Transaction tx=dao.getSession().beginTransaction();
        dao.save(entity);
        tx.commit();
    }
}
