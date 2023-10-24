package com.example.Hackaton;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Sponsor")
public class Sponsor {

    @Id
    @GeneratedValue
    private int idSponsor;
    private String nom;
    private String logoPath;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Sponsor(int idSponsor, String nom, String logoPath, Category category) {
        this.idSponsor = idSponsor;
        this.nom = nom;
        this.logoPath = logoPath;
        this.category = category;
    }

    public Sponsor() {

    }

    public int getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(int idSponsor) {
        this.idSponsor = idSponsor;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @ManyToMany(mappedBy = "sponsors")
    private Set<Hackaton> hackatons = new HashSet<>();


    // Getter for hackatons
    public Set<Hackaton> getHackatons() {
        return hackatons;
    }
}
