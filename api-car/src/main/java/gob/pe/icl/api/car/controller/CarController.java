package gob.pe.icl.api.car.controller;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Car;
import gob.pe.icl.service.inter.InterServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Car>> findAllCars() throws UnknownException{
        if(interServiceCar.findAllCars() == null)
            return ResponseEntity.notFound().build();
        List<Car> cars = interServiceCar.findAllCars();
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> findByUserId(@PathVariable("userId") Long userId) throws UnknownException {
        List<Car> cars = interServiceCar.findByUserId(userId);
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);

    }
}

