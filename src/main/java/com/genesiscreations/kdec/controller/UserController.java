package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.NotificationInfo;
import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.model.User;
import com.genesiscreations.kdec.repository.NotificationRepository;
import com.genesiscreations.kdec.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;
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
        String uid = verifyToken(idToken);
        user.setUid(uid);
        System.out.println("yyooo");
        System.out.println(uid);
        return  ResponseEntity.ok().body(userRepository.save(user));
    }
    @PostMapping("/notification/add/{idToken}")
    public  ResponseEntity<NotificationInfo> addNotification(@RequestBody NotificationInfo n, @PathVariable("idToken") String idToken) throws FirebaseAuthException {
        String uid = verifyToken(idToken);
        Optional<User> user = userRepository.findById(uid);
        if(user.isPresent()){
          n.setUser(user.get());
            return ResponseEntity.ok().body(notificationRepository.save(n));
        }
        return  ResponseEntity.notFound().build();
    }

    private String verifyToken(String token) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().verifyIdToken(token).getUid();
    }
}
