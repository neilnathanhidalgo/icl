package gob.pe.icl.api.user.controller;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.inter.InterServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    InterServiceUser interServiceUser;
    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) throws UnknownException {
        User userNew;
        userNew = interServiceUser.saveUser(user);
        return ResponseEntity.ok(userNew);
    }

}
