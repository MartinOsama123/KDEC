package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.Note;
import com.genesiscreations.kdec.model.NotificationInfo;
import com.genesiscreations.kdec.model.User;
import com.genesiscreations.kdec.repository.FirebaseMessagingRepository;
import com.genesiscreations.kdec.repository.NotificationRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private FirebaseMessagingRepository firebaseMessagingRepository;
    @GetMapping("/notification/{idToken}")
    public ResponseEntity<List<NotificationInfo>> getNotifications(@PathVariable("idToken") String idToken) throws FirebaseAuthException {
        String uid = verifyToken(idToken);
        return  ResponseEntity.ok().body(notificationRepository.getNotifications(uid));
    }
    @GetMapping("/notification/")
    public ResponseEntity<List<NotificationInfo>> getAllNotifications() throws FirebaseAuthException {

        return  ResponseEntity.ok().body(notificationRepository.findAll());
    }
    @PostMapping("/notification/add/{idToken}")
    public ResponseEntity<NotificationInfo> addNotification(@RequestBody NotificationInfo n, @PathVariable("idToken") String idToken) throws FirebaseAuthException, FirebaseMessagingException {
        String uid = verifyToken(idToken);
        System.out.println(uid);
        User user = notificationRepository.getUser(uid);
            n.setUser(user);
            n.setSendAt(LocalDateTime.now());
            firebaseMessagingRepository.sendNotification(new Note(n.getTitle(),n.getBody(),"Global"));
            return ResponseEntity.ok().body(notificationRepository.save(n));
    }
    private String verifyToken(String token) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().verifyIdToken(token).getUid();
    }
}
