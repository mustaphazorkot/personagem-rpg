package com.example.rpg_personagem.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Personagem.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Personagem {

    public interface CreatePersonagem {
    }

    public interface UpdatePersonagem {
    }

    public static final String TABLE_NAME = "personagem";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "raca_id", nullable = false)
    private Raca raca;

    @Column(name = "nome")
    @Size(min = 1, max = 100)
    @NotBlank(groups = { CreatePersonagem.class, UpdatePersonagem.class })
    private String nome;

    @Column(name = "forca")
    private Integer forca;

    @Column(name = "destreza")
    private Integer destreza;

    @Column(name = "constituicao")
    private Integer constituicao;

    @Column(name = "inteligencia")
    private Integer inteligencia;

    @Column(name = "sabedoria")
    private Integer sabedoria;

    @Column(name = "carisma")
    private Integer carisma;

}
