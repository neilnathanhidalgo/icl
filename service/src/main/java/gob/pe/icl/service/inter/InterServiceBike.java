package gob.pe.icl.service.inter;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;

import java.util.List;

public interface InterServiceBike {
    Bike getBikeById(Long id) throws UnknownException;
    List<Bike> findAll() throws UnknownException;
    Bike saveBike(Bike bike) throws UnknownException;
    List<Bike> findByUserId(Long userId) throws UnknownException;

}
