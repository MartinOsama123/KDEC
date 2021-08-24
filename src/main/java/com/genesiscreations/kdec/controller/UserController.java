package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.NotificationInfo;
import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.model.User;
import com.genesiscreations.kdec.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
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
    @PostMapping("/users/create/{idToken}")
    public  ResponseEntity<User> createUser(@RequestBody User user,@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        user.setUid(uid);
        System.out.println(uid);
        return  ResponseEntity.ok().body(userRepository.save(user));
    }
    @PostMapping("/notification/add/{email}")
    public  ResponseEntity<User> addNotification(@RequestBody NotificationInfo n, @PathVariable("email") String email){
        Optional<User> user = userRepository.findById(email);
        if(user.isPresent()){
            user.get().getNotifications().add(n);
            return ResponseEntity.ok().body(userRepository.save(user.get()));
        }
        return  ResponseEntity.notFound().build();
    }
}
