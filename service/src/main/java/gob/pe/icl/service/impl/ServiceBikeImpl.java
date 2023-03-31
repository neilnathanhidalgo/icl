package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoBike;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.inter.InterServiceBike;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class ServiceBikeImpl implements InterServiceBike {

    @Autowired
    private InterDaoBike daoBike;
    @Autowired
    private InterDaoUser daoUser;
    @Override
    public Bike getBikeById(Long id) throws UnknownException {
        Transaction tx = daoBike.getSession().beginTransaction();
        Bike bike;
        try {
            bike = daoBike.findById(id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener la moto con id " + id);
        }
        return bike;
    }
    @Override
    public Collection<Bike> findAllBikes() throws UnknownException {
        Transaction tx = daoBike.getSession().beginTransaction();
        Collection<Bike> bikes;
        try {
            bikes = daoBike.customFields("id, brand, model, version");
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener las motos");
        }
        return bikes;
    }

    @Override
    public Bike saveBike(Bike bike) throws UnknownException {
        Transaction tx = daoBike.getSession().beginTransaction();
        try {
            bike.setIsPersistente(Boolean.TRUE);
            bike.setVersion((new Date()).getTime());
            daoBike.save(bike);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
            throw new UnknownException(ServiceUserImpl.class, "No se a podido crear la moto");
        }
        return bike;
    }
    @Override
    public Bike updateBike(Bike bike) throws UnknownException {
        Transaction tx = daoBike.getSession().beginTransaction();
        Bike updatedBike;
        try {
            Bike existingBike = daoBike.findById(bike.getId());
            existingBike.setBrand(bike.getBrand());
            existingBike.setModel(bike.getModel());
            existingBike.setVersion((new Date()).getTime());
            daoBike.update(existingBike);
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
        Transaction tx = daoBike.getSession().beginTransaction();
        try {
            Bike bike = daoBike.findById(id);
            if (bike != null) {
                daoBike.delete(bike);
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
