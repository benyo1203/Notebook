package com.example.nootbook;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "processzor")
public class Processzor {
    @Id
    private int id;

    @Column(name = "gyarto")
    private String gyártó;

    @Column(name = "tipus")
    private String típus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGyártó() {
        return gyártó;
    }

    public void setGyártó(String gyártó) {
        this.gyártó = gyártó;
    }

    public String getTípus() {
        return típus;
    }

    public void setTípus(String típus) {
        this.típus = típus;
    }
}