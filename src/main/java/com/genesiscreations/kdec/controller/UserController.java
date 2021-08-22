package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.model.User;
import com.genesiscreations.kdec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return  ResponseEntity.ok().body(userRepository.findAll());
    }
    @GetMapping("/users/{email}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("email") String email) {
        return  ResponseEntity.ok().body(userRepository.findById(email));
    }
    @PostMapping("/users/create")
    public  ResponseEntity<User> createUser(@RequestBody User user){
        return  ResponseEntity.ok().body(userRepository.save(user));
    }
}
