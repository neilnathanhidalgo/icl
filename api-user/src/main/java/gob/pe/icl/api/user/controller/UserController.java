package gob.pe.icl.api.user.controller;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.impl.ServiceUserImpl;
import gob.pe.icl.service.inter.InterServiceUser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {
    @Autowired
    InterServiceUser interServiceUser;

    @Autowired
    ServiceUserImpl serviceUser;

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) throws UnknownException {
        return ResponseEntity.ok(interServiceUser.saveUser(user));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws UnknownException {
        if(interServiceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        User user = interServiceUser.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) throws UnknownException {
        if(interServiceUser.findByUsername(username) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(interServiceUser.findByUsername(username));
    }
    @GetMapping()
    public ResponseEntity<Collection<User>> findAllUsers() throws UnknownException{
        if(interServiceUser.findAllUsers() == null)
            return ResponseEntity.notFound().build();
        Collection<User> users = interServiceUser.findAllUsers();
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
    public ResponseEntity<Car> saveCar(@PathVariable("userId") Long userId, @RequestBody Car car) throws UnknownException {
        if(serviceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Car carNew = serviceUser.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }
    public ResponseEntity<Object> fallbackPostCarMethod(Long userId, Car car, Throwable e) {
        log.info("Ha ocurrido un error intentando guardar la data:" + e.getMessage());
        return ResponseEntity.badRequest().body("Ha ocurrido un problema al intentar guardar los datos.");
    }
    @PostMapping("/savebike/{userId}")
    @CircuitBreaker(name = "api-bike-dev", fallbackMethod = "fallbackPostBikeMethod")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") Long userId, @RequestBody Bike bike) throws UnknownException {
        if(serviceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Bike bikeNew = serviceUser.saveBike(userId, bike);
        return  ResponseEntity.ok(bikeNew);
    }
    public ResponseEntity<Object> fallbackPostBikeMethod(Long userId, Bike bike, Throwable e) {
        log.info("Ha ocurrido un error intentando guardar la data:" + e.getMessage());
        return ResponseEntity.badRequest().body("Ha ocurrido un problema al intentar enviar los datos.");
    }
    @GetMapping("/bikes/{userId}")
    @CircuitBreaker(name = "api-bike-dev", fallbackMethod = "fallbackGetMethod")
    public ResponseEntity<List<Bike>> findBikesByUserId(@PathVariable("userId") Long userId) throws UnknownException {
        if(serviceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        List<Bike> userBikes = serviceUser.findBikesByUserId(userId);
        return ResponseEntity.ok(userBikes);
    }
    @GetMapping("/cars/{userId}")
    @CircuitBreaker(name = "api-car-dev", fallbackMethod = "fallbackGetMethod")
    public ResponseEntity<List<Car>> findCarsByUserId(@PathVariable("userId") Long userId) throws UnknownException {
        if(serviceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        List<Car> userCars = serviceUser.findCarsByUserId(userId);
        return ResponseEntity.ok(userCars);
    }
    public ResponseEntity<Object> fallbackGetMethod(Long userId, Throwable e) {
        log.info("Ha ocurrido un error intentando obtener la data:" + e.getMessage());
        return ResponseEntity.badRequest().body("Ha ocurrido un problema al intentar obtener los datos.");
    }
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> findVehicles(@PathVariable("userId") Long userId) throws UnknownException {
        Map<String, Object> result = serviceUser.findVehicles(userId);
        return ResponseEntity.ok(result);
    }
}
