package org.example.consegna_cartelloni.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "palinsesti")
public class Palinsesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //!!!TODO INFORMARCI
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String nome;

    @OneToMany(mappedBy = "palinsesto")
    private List<Impianto> impianti;

}

