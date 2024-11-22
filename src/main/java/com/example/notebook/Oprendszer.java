package com.example.notebook;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "oprendszer")
public class Oprendszer {
    @Id
    private int id;

    @Column(name = "nev")
    private String név;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNév() {
        return név;
    }

    public void setNév(String név) {
        this.név = név;
    }

}
