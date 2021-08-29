package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.Note;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return  ResponseEntity.ok().body(userRepository.findAll());
    }
    @GetMapping("/users/subscriptions/{idToken}")
    public ResponseEntity<Set<String>> getAllSubs(@PathVariable("idToken") String idToken) throws FirebaseAuthException {

        return  ResponseEntity.ok().body(userRepository.findSubsById(verifyToken(idToken)));
    }
    @GetMapping("/users/{idToken}")
    public ResponseEntity<User> getUser(@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        Optional<User> user = userRepository.findById(verifyToken(idToken));
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/users/create/{idToken}")
    public  ResponseEntity<User> createUser(@RequestBody User user,@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        String uid = verifyToken(idToken);
        user.setUid(uid);
        return  ResponseEntity.ok().body(userRepository.save(user));
    }
    @PostMapping("/users/subscription/{topic}/{idToken}")
    public  ResponseEntity<User> addSub(@PathVariable("topic") String topic,@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        String uid = verifyToken(idToken);
        System.out.println(uid);
        Optional<User> user = userRepository.findById(uid);
        user.ifPresent(value -> value.getSubs().add(topic));

        return ResponseEntity.ok().body(userRepository.save(user.get()));
    }
    @DeleteMapping("/users/subscription/{topic}/{idToken}")
    public  ResponseEntity<User> deleteSub(@PathVariable("topic") String topic,@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        String uid = verifyToken(idToken);
        System.out.println(uid);
        Optional<User> user = userRepository.findById(uid);
        user.ifPresent(value -> value.getSubs().remove(topic));

        return ResponseEntity.ok().body(userRepository.save(user.get()));
    }


    private String verifyToken(String token) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().verifyIdToken(token).getUid();
    }
}
