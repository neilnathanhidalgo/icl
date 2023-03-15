/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Car;
import gob.pe.icl.service.inter.InterServiceCar;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Usuario
 */
public class TestServiceCarImpl extends TestBaseService {

    @Test
    void createCar() throws UnknownException {
        InterServiceCar service = contextService.getBean(ServiceCarImpl.class);
        Car entity = contextEntity.getBean(Car.class);
        entity.setBrand("Jonathan");
        entity.setModel("chescot2302@gmail.com");
        service.saveCar(entity);
    }
}
