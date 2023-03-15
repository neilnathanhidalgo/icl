package gob.pe.icl.api.bike.controller;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.service.inter.InterServiceBike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    InterServiceBike interServiceBike;
    @PostMapping()
    public ResponseEntity<Bike> save(@RequestBody Bike bike) throws UnknownException {
        Bike bikeNew;
        bikeNew = interServiceBike.saveBike(bike);
        return ResponseEntity.ok(bikeNew);
    }
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> findByUserId(@PathVariable("userId") Long userId) throws UnknownException {
        List<Bike> bikes = interServiceBike.findByUserId(userId);
        if (bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);

    }

}
