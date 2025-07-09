package com.github.redfox197;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Product extends PanacheEntity {
    @Column(nullable = false)
    public String code;

    public String name;

    @Column(columnDefinition = "DECIMAL(10,2)", nullable = false)
    public double price;
}
