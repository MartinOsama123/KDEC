package com.genesiscreations.kdec.repository;


import com.genesiscreations.kdec.model.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import org.springframework.stereotype.Service;

@Service
    public class FirebaseMessagingRepository {

        private final FirebaseMessaging firebaseMessaging;

        public FirebaseMessagingRepository(FirebaseMessaging firebaseMessaging) {
            this.firebaseMessaging = firebaseMessaging;
        }


        public String sendNotification(Note note) throws FirebaseMessagingException, FirebaseAuthException {

            Notification notification = Notification
                    .builder()
                    .setTitle(note.getTitle())
                    .setBody(note.getMessage())
                    .build();

            Message message = Message
                    .builder()
                    .setTopic(note.getTopic())
                    .setNotification(notification)
                   // .putAllData(note.getData())
                    .build();
            return firebaseMessaging.send(message);
        }

    }

