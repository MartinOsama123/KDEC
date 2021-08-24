package com.genesiscreations.kdec.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "notifications")
public class NotificationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    String body;
    @DateTimeFormat(fallbackPatterns = "dd/MM/yyyy")
     Date birthDay;

    public NotificationInfo(String title, String body, Date birthDay) {
        this.title = title;
        this.body = body;
        this.birthDay = birthDay;
    }
}
