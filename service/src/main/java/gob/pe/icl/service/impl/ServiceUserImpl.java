/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.feign.BikeFeign;
import gob.pe.icl.service.feign.CarFeign;
import gob.pe.icl.service.inter.InterServiceUser;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;


@Service
public class ServiceUserImpl implements InterServiceUser {

    @Autowired
    private InterDaoUser dao;
    @Autowired
    CarFeign carFeign;
    @Autowired
    BikeFeign bikeFeign;

    @Override
    public User getUserById(Long id) throws  UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        User user;
        try {
            user = dao.findById(id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener el usuario con id " + id);
        }
        return user;
    }
    @Override
    public User findByUsername(String username) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        User user;
        try {
            user = dao.findByUsername(username);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener el usuario con username " + username);
        }
        return user;
    }
    @Override
    public User saveUser(User entidad) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        try {
            entidad.setIsPersistente(Boolean.TRUE);
            entidad.setVersion((new Date()).getTime());
            entidad.setEnabled(Boolean.TRUE);
            dao.save(entidad);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se a podido crear el usuario");
        }
        return entidad;
    }
    @Override
    public Collection<User> findAllUsers() throws  UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        Collection<User> users;
        try {
            users = dao.allFields();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener los usuarios");
        }
        return users;
    }
    @Override
    public User updateUser(User user) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        User updatedUser;
        try {
            User existingUser = dao.findById(user.getId());
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setVersion((new Date()).getTime());
            dao.update(existingUser);
            tx.commit();
            updatedUser = existingUser;
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo actualizar el usuario con id " + user.getId());
        }
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        try {
            User user = dao.findById(userId);
            if (user != null) {
                dao.delete(user);
                tx.commit();
            } else {
                throw new UnknownException(ServiceUserImpl.class, "No se pudo encontrar el usuario con id " + userId);
            }
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo eliminar el usuario con id " + userId);
        }
    }
    public Car saveCar(Long userId, Car car) throws UnknownException{
        car.setUserId(userId);
        return carFeign.saveCar(car);
    }
    public Bike saveBike(Long userId, Bike bike) throws UnknownException {
        bike.setUserId(userId);
        return bikeFeign.saveBike(bike);
    }
    public List<Bike> findBikesByUserId(Long userId) throws UnknownException {
        return bikeFeign.findBikesByUserId(userId);
    }
    public List<Car> findCarsByUserId(Long userId) throws UnknownException {
        return carFeign.findCarsByUserId(userId);
    }
    public Map<String, Object> findVehicles(Long userId) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        User user = dao.findById(userId);
        List<Bike> bikes = bikeFeign.findBikesByUserId(userId);
        List<Car> cars = carFeign.findCarsByUserId(userId);
        Map<String, Object> result = new HashMap<>();
        if (user == null) {
            result.put("Mensaje", "No existe el usuario");
            return result;
        }
        result.put("User", user);
        if(cars.isEmpty())
            result.put("Mensaje", "Este usuario no tiene autos");
        else
            result.put("Cars", cars);
        if(bikes.isEmpty())
            result.put("Mensaje", "Este usuario no tiene motos");
        else
            result.put("Bikes", bikes);

        return result;
    }
}
