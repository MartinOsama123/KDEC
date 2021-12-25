package com.genesiscreations.kdec.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")

public class User {
    @Id
    String uid;
    String email;
    String name;
    String phone;

    @OneToMany(fetch= FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "user")
    List<NotificationInfo> notifications;
    @ElementCollection

    @CollectionTable(name = "user_subs", joinColumns = @JoinColumn(name = "user_uid"))
    Set<String> subs;
    @ElementCollection
    @CollectionTable(name = "user_messages", joinColumns = @JoinColumn(name = "user_uid"))
    List<String> messages;
    int age;
public User(){
}
    public User(String email, String name, String phone, List<NotificationInfo> notifications, Set<String> subs, List<String> messages,int age) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.notifications = notifications;
        this.subs = subs;
        this.messages = messages;
        this.age = age;
    }

    public void addNotification(NotificationInfo n){
        this.notifications.add(n);
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<NotificationInfo> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationInfo> notifications) {
        this.notifications = notifications;
    }

    public Set<String> getSubs() {
        return subs;
    }

    public void setSubs(Set<String> subs) {
        this.subs = subs;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
