package com.genesiscreations.kdec.model;

import lombok.Data;

import java.util.Map;


public class Note {
    private String title;
    private String message;
    private String topic;
    private String token;

    public Note(String title, String message, String topic) {
        this.title = title;
        this.message = message;
        this.topic = topic;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}