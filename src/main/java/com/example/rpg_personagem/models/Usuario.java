package com.example.rpg_personagem.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.rpg_personagem.models.enuns.PerfilEnun;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = Access.WRITE_ONLY)
    @CollectionTable(name = "perfil_usuario")
    @Column(name = "perfil", nullable = false)
    private Set<Integer> perfis = new HashSet<>();

    public Set<PerfilEnun> getPerfis(){
        return this.perfis.stream().map(x -> PerfilEnun.toEnun(x)).collect(Collectors.toSet());
    }

    public void addPerfil(PerfilEnun perfilEnun){
        this.perfis.add(perfilEnun.getCode());
    }
}
