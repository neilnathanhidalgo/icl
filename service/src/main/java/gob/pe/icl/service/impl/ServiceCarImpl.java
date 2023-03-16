package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoCar;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.inter.InterServiceCar;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceCarImpl implements InterServiceCar {

    @Autowired
    private InterDaoCar dao;

    @Override
    public Car getCarById(Long id) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        Car car;
        try {
            car = dao.findById(id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener el auto con id " + id);
        }
        return car;
    }
    @Override
    public List<Car> findAllCars() throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        List<Car> cars;
        try {
            cars = dao.findAll();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceCarImpl.class, "No se pudieron obtener todos los carros");
        }
        return cars;
    }
    @Override
    public Car saveCar(Car car) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        try {
            car.setIsPersistente(Boolean.TRUE);
            car.setVersion((new Date()).getTime());
            dao.save(car);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se a podido crear el aauto");
        }
        return car;
    }
    @Override
    public List<Car> findByUserId(Long userId) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        List<Car> cars;
        try {
            cars = dao.findByUserId(userId);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceCarImpl.class, "No se pudieron obtener los carros del usuario con id " + userId);
        }
        return cars;
    }
    @Override
    public Car updateCar(Car car) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        Car updatedCar;
        try {
            Car existingCar = dao.findById(car.getId());
            existingCar.setBrand(car.getBrand());
            existingCar.setModel(car.getModel());
            existingCar.setVersion((new Date()).getTime());
            dao.update(existingCar);
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
        Transaction tx = dao.getSession().beginTransaction();
        try {
            Car car = dao.findById(id);
            if (car != null) {
                dao.delete(car);
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

