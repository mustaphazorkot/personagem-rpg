package com.example.rpg_personagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rpg_personagem.models.Classe;
import com.example.rpg_personagem.repositories.ClasseRepository;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    public Classe findById(Long id) {
        Optional<Classe> classe = this.classeRepository.findById(id);
        return classe.orElseThrow(() -> new RuntimeException(
                "Classe não encontrada! Id: " + id + ", Tipo: " + Classe.class.getName()));
    }

    public Iterable<Classe> findAll(){
        Iterable<Classe> classe = this.classeRepository.findAll();
        return classe;
    }

    @Transactional
    public Classe create(Classe obj) {
        obj.setId(null);
        obj = this.classeRepository.save(obj);
        return obj;
    }

    public void delete(Long id) {
        findById(id);
        this.classeRepository.deleteById(id);
    }
}
