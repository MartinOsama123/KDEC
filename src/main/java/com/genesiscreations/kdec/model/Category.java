package com.genesiscreations.kdec.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String categoryTitle;

    public Category() {
    }

    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryName) {
        this.categoryTitle = categoryName;
    }
}
