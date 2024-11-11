package com.example.rpg_personagem.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.rpg_personagem.models.Personagem;


public interface PersonagemRepository extends CrudRepository<Personagem, Long> {

    Iterable<Personagem> findByUsuarioId(Long user_id);

}
