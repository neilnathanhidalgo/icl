package gob.pe.icl.api.user.controller;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import gob.pe.icl.entity.Car;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.impl.ServiceUserImpl;
import gob.pe.icl.service.inter.InterServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    InterServiceUser interServiceUser;

    @Autowired
    ServiceUserImpl serviceUser;

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) throws UnknownException {
        User userNew;
        userNew = interServiceUser.saveUser(user);
        return ResponseEntity.ok(userNew);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) throws UnknownException {
        if(interServiceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        User user = interServiceUser.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping()
    public ResponseEntity<List<User>> findAllUsers() throws UnknownException{
        if(interServiceUser.findAllUsers() == null)
            return ResponseEntity.notFound().build();
        List<User> users = interServiceUser.findAllUsers();
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
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") Long userId, @RequestBody Car car) throws UnknownException {
        if(serviceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Car carNew = serviceUser.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") Long userId, @RequestBody Bike bike) throws UnknownException {
        if(serviceUser.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Bike bikeNew = serviceUser.saveBike(userId, bike);
        return  ResponseEntity.ok(bikeNew);
    }
}
