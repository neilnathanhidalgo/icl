package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoBike;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.service.inter.InterServiceBike;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceBikeImpl implements InterServiceBike {

    @Autowired
    private InterDaoBike dao;

    @Override
    public Bike getBikeById(Long id) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        Bike bike;
        try {
            bike = dao.findById(id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener el usuario con id " + id);
        }
        return bike;
    }
    @Override
    public List<Bike> findAll() throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        List<Bike> bikes;
        try {
            bikes = dao.findAll();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceCarImpl.class, "No se pudieron obtener todos los carros");
        }
        return bikes;
    }
    @Override
    public Bike saveBike(Bike bike) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        try {
            bike.setIsPersistente(Boolean.TRUE);
            bike.setVersion((new Date()).getTime());
            dao.save(bike);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se a podido crear el usuario");
        }
        return bike;
    }
    @Override
    public List<Bike> findByUserId(Long userId) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        List<Bike> bikes;
        try {
            bikes = dao.findByUserId(userId);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceCarImpl.class, "No se pudieron obtener los carros del usuario con id " + userId);
        }
        return bikes;
    }
}
