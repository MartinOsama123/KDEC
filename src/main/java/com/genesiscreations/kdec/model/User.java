package com.genesiscreations.kdec.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    String uid;
    String email;
    String name;
    String phone;

    @OneToMany(fetch= FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "user")
    List<NotificationInfo> notifications;
    @ElementCollection(targetClass = String.class)
    List<String> subs;
public User(){
}
    public User(String email, String name, String phone, List<NotificationInfo> notifications, List<String> subs) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.notifications = notifications;
        this.subs = subs;
    }

    public void addNotification(NotificationInfo n){
        this.notifications.add(n);
    }

}
