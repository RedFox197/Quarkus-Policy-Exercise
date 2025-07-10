package com.github.redfox197;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Policy extends PanacheEntity {
    @Column(nullable = false)
    public String code;

    public Long product_id;

    @Column(nullable = false)
    public LocalDate creationDate;

    @Column(nullable = false)
    public LocalDate expirationDate;
}
