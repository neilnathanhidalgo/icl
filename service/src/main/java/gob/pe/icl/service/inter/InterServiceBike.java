package gob.pe.icl.service.inter;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;

import java.util.List;

public interface InterServiceBike {
    Bike getBikeById(Long id) throws UnknownException;
    List<Bike> findAllBikes() throws UnknownException;
    Bike saveBike(Bike bike) throws UnknownException;
    Bike updateBike(Bike bike) throws UnknownException;
    void deleteBike(Long id) throws UnknownException;


}
