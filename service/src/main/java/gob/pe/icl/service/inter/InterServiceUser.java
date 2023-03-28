/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gob.pe.icl.service.inter;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface InterServiceUser {

    User getUserById(Long id) throws UnknownException;
    User saveUser(User entidad)throws UnknownException;
    Collection<User> findAll() throws UnknownException;
    User updateUser(User user) throws UnknownException;
    void deleteUser(Long userId) throws UnknownException;
    Car saveCar(Long userId, Car car) throws UnknownException;
    Bike saveBike(Long userId, Bike bike) throws UnknownException;
    List<Car> findCarsByUserId(Long user_id) throws UnknownException;
    List<Bike> findBikesByUserId(Long userId) throws UnknownException;
    Map<String, Object> findVehicles(Long userId) throws UnknownException;

}
