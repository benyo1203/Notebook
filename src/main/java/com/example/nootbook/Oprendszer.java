package com.example.nootbook;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "oprendszer")
public class Oprendszer {
    @Id
    public int id;

    @Column(name = "nev")
    public String név;

}
