package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoCar;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.inter.InterServiceCar;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ServiceCarImpl implements InterServiceCar {

    @Autowired
    private InterDaoCar daoCar;

    @Override
    public Car getCarById(Long id) throws UnknownException {
        Transaction tx = daoCar.getSession().beginTransaction();
        Car car;
        try {
            car = daoCar.findById(id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener el auto con id " + id);
        }
        return car;
    }
    @Override
    public Collection<Car> findAllCars() throws UnknownException {
        Transaction tx = daoCar.getSession().beginTransaction();
        Collection<Car> cars;
        try {
            cars = daoCar.allFields();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener los autos");
        }
        return cars;
    }
    @Override
    public Car saveCar(Car car) throws UnknownException {
        Transaction tx = daoCar.getSession().beginTransaction();
        try {
            car.setIsPersistente(Boolean.TRUE);
            car.setVersion((new Date()).getTime());
            daoCar.save(car);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se a podido crear el aauto");
        }
        return car;
    }
    @Override
    public Car updateCar(Car car) throws UnknownException {
        Transaction tx = daoCar.getSession().beginTransaction();
        Car updatedCar;
        try {
            Car existingCar = daoCar.findById(car.getId());
            existingCar.setBrand(car.getBrand());
            existingCar.setModel(car.getModel());
            existingCar.setVersion((new Date()).getTime());
            daoCar.update(existingCar);
            tx.commit();
            updatedCar = existingCar;
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo actualizar el auto con id " + car.getId());
        }
        return updatedCar;
    }
    @Override
    public void deleteCar(Long id) throws UnknownException {
        Transaction tx = daoCar.getSession().beginTransaction();
        try {
            Car car = daoCar.findById(id);
            if (car != null) {
                daoCar.delete(car);
                tx.commit();
            } else {
                throw new UnknownException(ServiceUserImpl.class, "No se pudo encontrar el auto con id " + id);
            }
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo eliminar el auto con id " + id);
        }
    }
}

