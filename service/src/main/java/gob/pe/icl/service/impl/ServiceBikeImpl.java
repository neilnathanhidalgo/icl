package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoBike;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
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
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener la moto con id " + id);
        }
        return bike;
    }
    @Override
    public List<Bike> findAllBikes() throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        List<Bike> bikes;
        try {
            bikes = dao.findAll();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceCarImpl.class, "No se pudieron obtener todos las motos");
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
            throw new UnknownException(ServiceUserImpl.class, "No se a podido crear la moto");
        }
        return bike;
    }
    @Override
    public Bike updateBike(Bike bike) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        Bike updatedBike;
        try {
            Bike existingBike = dao.findById(bike.getId());
            existingBike.setBrand(bike.getBrand());
            existingBike.setModel(bike.getModel());
            existingBike.setVersion((new Date()).getTime());
            dao.update(existingBike);
            tx.commit();
            updatedBike = existingBike;
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo actualizar la moto con id " + bike.getId());
        }
        return updatedBike;
    }
    @Override
    public void deleteBike(Long id) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        try {
            Bike bike = dao.findById(id);
            if (bike != null) {
                dao.delete(bike);
                tx.commit();
            } else {
                throw new UnknownException(ServiceUserImpl.class, "No se pudo encontrar la moto con id " + id);
            }
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo eliminar la moto con id " + id);
        }
    }
}
