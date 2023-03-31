package gob.pe.icl.service.inter;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;

import java.util.Collection;
import java.util.List;

public interface InterServiceCar {
    Car getCarById(Long id) throws UnknownException;
    Collection<Car> findAllCars() throws UnknownException;
    Car saveCar(Car car) throws UnknownException;
    Car updateCar(Car car) throws UnknownException;
    void deleteCar(Long id) throws UnknownException;


}
