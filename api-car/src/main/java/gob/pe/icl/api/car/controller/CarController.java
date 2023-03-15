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

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> findByUserId(@PathVariable("userId") Long userId) throws UnknownException {
        List<Car> cars = interServiceCar.findByUserId(userId);
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);

    }
}

