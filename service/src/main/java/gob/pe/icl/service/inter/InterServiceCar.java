package gob.pe.icl.service.inter;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;

import java.util.List;

public interface InterServiceCar {

    Car getCarById(Long id) throws UnknownException;
    List<Car> findAll() throws UnknownException;
    Car saveCar(Car car) throws UnknownException;
    List<Car> findByUserId(Long userId) throws UnknownException;

}
