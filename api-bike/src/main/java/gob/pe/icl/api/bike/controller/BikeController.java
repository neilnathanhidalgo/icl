package gob.pe.icl.api.bike.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.service.inter.InterServiceBike;
import gob.pe.icl.views.PublicView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/bike")
@JsonView(PublicView.class)
public class BikeController {
    @Autowired
    InterServiceBike interServiceBike;
    @PostMapping()
    public ResponseEntity<Bike> save(@RequestBody Bike bike) throws UnknownException {
        Bike bikeNew;
        bikeNew = interServiceBike.saveBike(bike);
        return ResponseEntity.ok(bikeNew);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable Long id) throws UnknownException {
        if(interServiceBike .getBikeById(id) == null)
            return ResponseEntity.notFound().build();
        Bike bike = interServiceBike.getBikeById(id);
        return ResponseEntity.ok(bike);
    }
    @GetMapping()
    public ResponseEntity<Collection<Bike>> findAllBikes() throws UnknownException{
        if(interServiceBike.findAllBikes() == null)
            return ResponseEntity.notFound().build();
        Collection<Bike> bikes = interServiceBike.findAllBikes();
        return ResponseEntity.ok(bikes);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Bike> updateCar(@PathVariable Long id, @RequestBody Bike bike) throws UnknownException {
        bike.setId(id);
        Bike updatedBike = interServiceBike.updateBike(bike);
        return ResponseEntity.ok(updatedBike);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) throws UnknownException {
        if(interServiceBike.getBikeById(id) == null)
            return ResponseEntity.notFound().build();
        interServiceBike.deleteBike(id);
        return ResponseEntity.noContent().build();
    }

}
