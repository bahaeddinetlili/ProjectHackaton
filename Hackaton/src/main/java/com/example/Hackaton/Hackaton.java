package com.example.Hackaton;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hackaton {
    @Id
    @GeneratedValue
    private Integer idHackaton;
    private String nomHackathon;
    private String Description;
    private Date dateDebut;
    private Date dateFin;
    private Integer nbMaxEquipes;
    private Date dateCreation;


    @ManyToMany
    @JoinTable(
            name = "hackaton_sponsors",
            joinColumns = @JoinColumn(name = "hackaton_id"),
            inverseJoinColumns = @JoinColumn(name = "sponsor_id")
    )
    private Set<Sponsor> sponsors = new HashSet<>();

    public void addSponsor(Sponsor sponsor) {
        if (sponsors == null) {
            sponsors = new HashSet<>();
        }
        sponsors.add(sponsor);
    }

    public void removeSponsor(Sponsor sponsor) {
        if (sponsors != null) {
            sponsors.remove(sponsor);
        }
    }

}
