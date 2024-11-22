package com.example.notebook;

import jakarta.persistence.*;

@Entity
@Table(name = "gep")
public class Gep {
    @Id
    private int id;

    @Column(name = "gyarto")
    private String gyártó;

    @Column(name= "tipus")
    private String típus;

    @Column(name = "kijelzo")
    private int kijelző;

    @Column(name = "memoria")
    private int memória;

    @Column(name = "merevlemez")
    private int merevlemez;

    @Column(name = "videovezerlo")
    private String videóvezérlő;

    @Column(name = "ar")
    private int ár;

    @Column(name = "processzorid")
    private int processzorid;

    @Column(name = "oprendszerid")
    private int oprendszerid;

    @Column(name = "db")
    private int db;

    @OneToOne
    @JoinColumn(name = "id")
    private Oprendszer opid;

    @OneToOne
    @JoinColumn(name = "id")
    private Processzor procid;

    public Processzor getProcid() {
        return procid;
    }

    public void setProcid(Processzor procid) {
        this.procid = procid;
    }

    public Oprendszer getOpid() {
        return opid;
    }

    public void setOpid(Oprendszer opid) {
        this.opid = opid;
    }

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

    public int getKijelző() {
        return kijelző;
    }

    public void setKijelző(int kijelző) {
        this.kijelző = kijelző;
    }

    public int getMemória() {
        return memória;
    }

    public void setMemória(int memória) {
        this.memória = memória;
    }

    public int getMerevlemez() {
        return merevlemez;
    }

    public void setMerevlemez(int merevlemez) {
        this.merevlemez = merevlemez;
    }

    public String getVideóvezérlő() {
        return videóvezérlő;
    }

    public void setVideóvezérlő(String videóvezérlő) {
        this.videóvezérlő = videóvezérlő;
    }

    public int getÁr() {
        return ár;
    }

    public void setÁr(int ár) {
        this.ár = ár;
    }

    public int getProcesszorid() {
        return processzorid;
    }

    public void setProcesszorid(int processzorid) {
        this.processzorid = processzorid;
    }

    public int getOprendszerid() {
        return oprendszerid;
    }

    public void setOprendszerid(int oprendszerid) {
        this.oprendszerid = oprendszerid;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }
}
