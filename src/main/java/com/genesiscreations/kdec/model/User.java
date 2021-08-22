package com.genesiscreations.kdec.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    String email;
    String name;
    String phone;
  //  List<String> subs;

}
