package com.genesiscreations.kdec.model;

import lombok.Data;

import java.util.Map;

@Data
public class Note {
    private String title;
    private String message;
    private String topic;
    private String token;

}