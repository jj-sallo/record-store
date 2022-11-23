package com.github.jj_sallo.recordstore.controller;

import com.github.javafaker.Faker;
import com.github.jj_sallo.recordstore.entity.User;
import com.github.jj_sallo.recordstore.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Guardar
// Obtener todos
// Obtener uno
// Eliminar

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    @ResponseBody
    ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(value = "id") long id) {
        Optional<User> userdata = userRepository.findById(id);
        if (userdata.isPresent()) {
            try {
                User foundUser = userdata.get();
                foundUser.setEmail(user.getEmail());
                userRepository.save(foundUser);
                return new ResponseEntity<>(foundUser, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/generate")
    ResponseEntity<List<User>> generateUsers() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            userRepository.save(user);
        }
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.CREATED);
    }

    @PostMapping(headers = "Accept=application/json")
    ResponseEntity<User> newUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @DeleteMapping(value = "/{id}")
    ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
