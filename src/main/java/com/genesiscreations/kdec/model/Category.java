package com.genesiscreations.kdec.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
