package com.example.rpg_personagem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.rpg_personagem.models.Personagem;

public interface PersonagemRepository extends CrudRepository<Personagem, Long> {

    List<Personagem> findByUser_Id(Long id);

}
