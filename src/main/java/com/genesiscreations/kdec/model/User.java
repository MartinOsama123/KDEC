package com.genesiscreations.kdec.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    String email;
    String name;
    String phone;
    @Transient
    List<NotificationInfo> notifications;
  //  List<String> subs;

}
