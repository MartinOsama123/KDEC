package com.genesiscreations.kdec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.firebase.auth.UserInfo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "notifications")
public class NotificationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    String body;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDateTime sendAt;

    @ManyToOne
    @JoinColumn(name="user_uid")
     User user;

    public NotificationInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getBirthDay() {
        return sendAt;
    }

    public void setBirthDay(LocalDateTime birthDay) {
        this.sendAt = birthDay;
    }



    public void setUser(User user) {
        this.user = user;
    }
}
