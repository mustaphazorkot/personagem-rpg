package com.example.rpg_personagem.repositories;

import org.springframework.stereotype.Repository;
import com.example.rpg_personagem.models.Usuario;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
    
}
