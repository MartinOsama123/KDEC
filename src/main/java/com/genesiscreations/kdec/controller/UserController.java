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
import java.util.stream.Collectors;

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
       return userRepository.findById(verifyToken(idToken)).map(user -> ResponseEntity.ok().body(user.getSubs())).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/users/{idToken}")
    public ResponseEntity<User> getUser(@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        Optional<User> user = userRepository.findById(verifyToken(idToken));
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/users/messages")
    public ResponseEntity<List<User>> getMessages()  {
        List<User> users = userRepository.findAll().stream().filter(user -> !user.getMessages().isEmpty()).collect(Collectors.toList());
        return  ResponseEntity.ok().body(users);
    }
    @GetMapping("/users/count")
    public long list() {return userRepository.count();}
    @PostMapping("/messages/create/{idToken}")
    public  ResponseEntity<User> createMessage(@RequestBody String message,@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        String uid = verifyToken(idToken);
        Optional<User> user = userRepository.findById(uid);
       return user.map(value -> {
            value.getMessages().add(message);
            return ResponseEntity.ok().body(userRepository.save(value));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/users/create/{idToken}")
    public  ResponseEntity<User> createUser(@RequestBody User user,@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        String uid = verifyToken(idToken);
        user.setUid(uid);
        return  ResponseEntity.ok().body(userRepository.save(user));
    }
    @PostMapping("/users/subscription/{topic}/{idToken}")
    public  ResponseEntity<Set<String>> addSub(@PathVariable("topic") String topic,@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        String uid = verifyToken(idToken);
        Optional<User> user = userRepository.findById(uid);
        user.map(value -> {value.getSubs().add(topic);
            userRepository.save(value);
            return ResponseEntity.ok().body(user.get().getSubs());
        });
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/users/subscription/{topic}/{idToken}")
    public  ResponseEntity<User> deleteSub(@PathVariable("topic") String topic,@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        String uid = verifyToken(idToken);
        Optional<User> user = userRepository.findById(uid);
        user.map(value -> {value.getSubs().remove(topic);
            userRepository.save(value);
            return ResponseEntity.ok().body(user.get().getSubs());
        });
        return ResponseEntity.notFound().build();
    }


    private String verifyToken(String token) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().verifyIdToken(token).getUid();
    }
}
