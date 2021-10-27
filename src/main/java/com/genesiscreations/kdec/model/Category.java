package com.genesiscreations.kdec.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String categoryName;
}
