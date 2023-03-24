/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoBike;
import gob.pe.icl.dao.inter.InterDaoCar;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.feign.BikeFeign;
import gob.pe.icl.service.feign.CarFeign;
import gob.pe.icl.service.inter.InterServiceUser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;


@Service
public class ServiceUserImpl implements InterServiceUser {

    @Autowired
    private InterDaoUser daoUser;
    @Autowired
    private InterDaoCar daoCar;
    @Autowired
    private InterDaoBike daoBike;

    @Override
    public User getUserById(Long id) throws  UnknownException {
        Transaction tx = daoUser.getSession().beginTransaction();
        User user;
        try {
            user = daoUser.findById(id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener el usuario con id " + id);
        }
        return user;
    }
    @Override
    public User saveUser(User entidad) throws UnknownException {
        Transaction tx = daoUser.getSession().beginTransaction();
        try {
            entidad.setIsPersistente(Boolean.TRUE);
            entidad.setVersion((new Date()).getTime());
            daoUser.save(entidad);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se a podido crear el usuario");
        }
        return entidad;
    }
    @Override
    public List<User> findAllUsers() throws  UnknownException {
        Transaction tx = daoUser.getSession().beginTransaction();
        List<User> users;
        try {
            users = daoUser.findAllUsers();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener los usuarios");
        }
        return users;
    }
    @Override
    public User updateUser(User user) throws UnknownException {
        Transaction tx = daoUser.getSession().beginTransaction();
        User updatedUser;
        try {
            User existingUser = daoUser.findById(user.getId());
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setVersion((new Date()).getTime());
            daoUser.update(existingUser);
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
        Transaction tx = daoUser.getSession().beginTransaction();
        try {
            User user = daoUser.findById(userId);
            if (user != null) {
                daoUser.delete(user);
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
<<<<<<< Updated upstream
        car.setUserId(userId);
        Car carNew = carFeign.saveCar(car);
        return  carNew;
    }
    public Bike saveBike(Long userId, Bike bike) throws UnknownException {
        bike.setUserId(userId);
        Bike bikeNew = bikeFeign.saveBike(bike);
        return  bikeNew;
    }
    public List<Bike> findBikesByUserId(Long userId) throws UnknownException {
        List<Bike> userBikes = bikeFeign.findBikesByUserId(userId);
        return userBikes;
    }
    public List<Car> findCarsByUserId(Long userId) throws UnknownException {
        List<Car> userCars = carFeign.findCarsByUserId(userId);
        return userCars;
=======
        Transaction tx = daoUser.getSession().beginTransaction();
        try {
            User user = daoUser.findById(userId);
            if (user == null) {
                throw new UnknownException(ServiceUserImpl.class, "No se pudo encontrar el usuario con id " + userId);
            }
            car.setUser(user);
            car.setIsPersistente(Boolean.TRUE);
            car.setVersion((new Date()).getTime());
            daoCar.save(car);
            tx.commit();
            return car;
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo guardar el auto para el usuario con id " + userId);
        }
    }
    public Bike saveBike(Long userId, Bike bike) throws UnknownException {
        Transaction tx = daoUser.getSession().beginTransaction();
        try {
            User user = daoUser.findById(userId);
            if (user == null) {
                throw new UnknownException(ServiceUserImpl.class, "No se pudo encontrar el usuario con id " + userId);
            }
            bike.setUser(user);
            bike.setIsPersistente(Boolean.TRUE);
            bike.setVersion((new Date()).getTime());
            daoBike.save(bike);
            tx.commit();
            return bike;
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo guardar la moto para el usuario con id " + userId);
        }
    }
    public List<Bike> findBikesByUserId(Long userId) throws UnknownException {
        Transaction tx = daoUser.getSession().beginTransaction();
        try {
            User user = daoUser.findById(userId);
            if (user == null) {
                throw new UnknownException(ServiceUserImpl.class, "No se pudo encontrar el usuario con id " + userId);
            }
            List<Bike> bikes = user.getBikes();
            tx.commit();
            return bikes;
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo llamar la lista de motos para el usuario con id " + userId);
        }

    }
    public List<Car> findCarsByUserId(Long userId) throws UnknownException {
        Transaction tx = daoUser.getSession().beginTransaction();
        try {
            User user = daoUser.findById(userId);
            if (user == null) {
                throw new UnknownException(ServiceUserImpl.class, "No se pudo encontrar el usuario con id " + userId);
            }
            List<Car> cars = user.getCars();
            tx.commit();
            return cars;
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo llamar la lista de autos para el usuario con id " + userId);
        }
>>>>>>> Stashed changes
    }
    public Map<String, Object> findVehicles(Long userId) throws UnknownException {
        Transaction tx = daoUser.getSession().beginTransaction();
        User user = daoUser.findById(userId);
        List<Bike> bikes = user.getBikes();
        List<Car> cars = user.getCars();
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
