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
    @Transient
    @ElementCollection(targetClass = NotificationInfo.class)
    List<NotificationInfo> notifications = new ArrayList<>();
    @ElementCollection(targetClass = String.class)
    List<String> subs = new ArrayList<>();

}
