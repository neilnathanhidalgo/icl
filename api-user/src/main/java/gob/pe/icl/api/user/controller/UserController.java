package gob.pe.icl.api.user.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.impl.ServiceUserImpl;
import gob.pe.icl.service.inter.InterServiceUser;
import gob.pe.icl.views.BikeView;
import gob.pe.icl.views.CarView;
import gob.pe.icl.views.PublicView;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Log4j2
@Validated
public class UserController {
    @Autowired
    private InterServiceUser interServiceUser;
    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) throws UnknownException {
        return ResponseEntity.ok(interServiceUser.saveUser(user));
    }
    @GetMapping("/{userId}")
    @JsonView(PublicView.class)
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws UnknownException {
        User user = interServiceUser.getUserById(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping()
    @JsonView(PublicView.class)
    public ResponseEntity<Collection<User>> findAllUsers() throws UnknownException{
        if(interServiceUser.findAll().isEmpty())
            return ResponseEntity.notFound().build();
        Collection<User> users = interServiceUser.findAll();
        return ResponseEntity.ok(users);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) throws UnknownException {
        user.setId(userId);
        User updatedUser = interServiceUser.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UnknownException {
        if(interServiceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        interServiceUser.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // LLAMADAS FEIGN

    @PostMapping("/savecar/{userId}")
    @CircuitBreaker(name = "api-car-dev", fallbackMethod = "fallbackPostCarMethod")
    @JsonView(CarView.class)
    public ResponseEntity<Car> saveCar(@PathVariable("userId") Long userId, @RequestBody Car car) throws UnknownException {
        if(interServiceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Car carNew = interServiceUser.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }
    public ResponseEntity<Object> fallbackPostCarMethod(Long userId, Car car, Throwable e) {
        log.info("Ha ocurrido un error intentando guardar la data:" + e.getMessage());
        return ResponseEntity.badRequest().body("Ha ocurrido un problema al intentar guardar los datos.");
    }
    @PostMapping("/savebike/{userId}")
    @CircuitBreaker(name = "api-bike-dev", fallbackMethod = "fallbackPostBikeMethod")
    @JsonView(BikeView.class)
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") Long userId, @RequestBody Bike bike) throws UnknownException {
        if(interServiceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Bike bikeNew = interServiceUser.saveBike(userId, bike);
        return  ResponseEntity.ok(bikeNew);
    }
    public ResponseEntity<Object> fallbackPostBikeMethod(Long userId, Bike bike, Throwable e) {
        log.info("Ha ocurrido un error intentando guardar la data:" + e.getMessage());
        return ResponseEntity.badRequest().body("Ha ocurrido un problema al intentar enviar los datos.");
    }
    @GetMapping("/bikes/{userId}")
    @JsonView(BikeView.class)
    @CircuitBreaker(name = "api-bike-dev", fallbackMethod = "fallbackGetMethod")
    public ResponseEntity<List<Bike>> findBikesByUserId(@PathVariable("userId") Long userId) throws UnknownException {
        if(interServiceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        List<Bike> userBikes = interServiceUser.findBikesByUserId(userId);
        return ResponseEntity.ok(userBikes);
    }
    @GetMapping("/cars/{user_id}")
    @JsonView(CarView.class)
    @CircuitBreaker(name = "api-car-dev", fallbackMethod = "fallbackGetMethod")
    public ResponseEntity<List<Car>> findCarsByUserId(@PathVariable("user_id") Long user_id) throws UnknownException {
        if(interServiceUser.getUserById(user_id) == null)
            return ResponseEntity.notFound().build();
        List<Car> userCars = interServiceUser.findCarsByUserId(user_id);
        return ResponseEntity.ok(userCars);
    }
    public ResponseEntity<Object> fallbackGetMethod(Long userId, Throwable e) {
        log.info("Ha ocurrido un error intentando obtener la data:" + e.getMessage());
        return ResponseEntity.badRequest().body("Ha ocurrido un problema al intentar obtener los datos.");
    }
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> findVehicles(@PathVariable("userId") Long userId) throws UnknownException {
        Map<String, Object> result = interServiceUser.findVehicles(userId);
        return ResponseEntity.ok(result);
    }
}
