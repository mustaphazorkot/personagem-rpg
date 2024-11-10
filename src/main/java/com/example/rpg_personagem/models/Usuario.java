package com.example.rpg_personagem.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Usuario.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Usuario {

    public interface CreateUsuario {
    }

    public interface UpdateUsuario {
    }

    public static final String TABLE_NAME = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotBlank(groups = { CreateUsuario.class, UpdateUsuario.class })
    @Size(groups = { CreateUsuario.class, UpdateUsuario.class }, min = 2, max = 100)
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "senha", length = 100, nullable = false)
    @NotBlank(groups = { CreateUsuario.class, UpdateUsuario.class })
    @Size(groups = { CreateUsuario.class, UpdateUsuario.class }, min = 3, max = 255)
    private String senha;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank(groups = CreateUsuario.class)
    @Size(groups = CreateUsuario.class, min = 2, max = 100)
    private String nome;

    @Column(name = "apelido", length = 100, nullable = false, unique = true)
    @NotBlank(groups = CreateUsuario.class)
    @Size(groups = CreateUsuario.class, min = 2, max = 100)
    private String apelido;

    @OneToMany(mappedBy = "usuario")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Personagem> personagens = new ArrayList<Personagem>();

}
