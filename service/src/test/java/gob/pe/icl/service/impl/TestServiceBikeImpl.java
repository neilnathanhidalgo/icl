/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.service.inter.InterServiceBike;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Usuario
 */
public class TestServiceBikeImpl extends TestBaseService {

    @Test
    void createBike1() throws UnknownException {
        InterServiceBike service = contextService.getBean(ServiceBikeImpl.class);
        Bike entity = contextEntity.getBean(Bike.class);
        entity.setBrand("Jonathan");
        entity.setModel("chescot2302@gmail.com");
        service.saveBike(entity);
    }
}
