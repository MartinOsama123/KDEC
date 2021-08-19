package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.Note;
import com.genesiscreations.kdec.repository.FirebaseMessagingRepository;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FirebaseMessagingController {

    private final FirebaseMessagingRepository firebaseMessagingRepository;

    public FirebaseMessagingController(FirebaseMessagingRepository firebaseMessagingRepository) {
        this.firebaseMessagingRepository = firebaseMessagingRepository;
    }
    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note) throws FirebaseMessagingException, FirebaseAuthException {
        return firebaseMessagingRepository.sendNotification(note);
    }
}
