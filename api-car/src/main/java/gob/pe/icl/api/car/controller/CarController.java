package gob.pe.icl.api.car.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Car;
import gob.pe.icl.service.inter.InterServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    InterServiceCar interServiceCar;
    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car) throws UnknownException {
        Car carNew;
        carNew = interServiceCar.saveCar(car);
        return ResponseEntity.ok(carNew);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) throws UnknownException {
        if(interServiceCar.getCarById(id) == null)
            return ResponseEntity.notFound().build();
        Car car = interServiceCar.getCarById(id);
        return ResponseEntity.ok(car);
    }
    @GetMapping()
    public ResponseEntity<Collection<Car>> findAllCars() throws UnknownException{
        if(interServiceCar.findAllCars() == null)
            return ResponseEntity.notFound().build();
        Collection<Car> cars = interServiceCar.findAllCars();
        return ResponseEntity.ok(cars);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) throws UnknownException {
        car.setId(id);
        Car updatedCar = interServiceCar.updateCar(car);
        return ResponseEntity.ok(updatedCar);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) throws UnknownException {
        if(interServiceCar.getCarById(id) == null)
            return ResponseEntity.notFound().build();
        interServiceCar.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

}

