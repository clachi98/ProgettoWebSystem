package org.example.consegna_cartelloni.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "impianti")
public class Impianto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String latitudine;
    @Getter @Setter
    private String longitudine;
    @Getter @Setter
    private String descrizione;

    @Getter @Setter
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean stato;

    @Getter @Setter
    @Column(name = "palinsesto")
    private Integer palinsesto;

    // Getters and Setters

}
