package com.example.rpg_personagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rpg_personagem.models.Raca;
import com.example.rpg_personagem.repositories.RacaRepository;

@Service
public class RacaService {

    @Autowired
    private RacaRepository racaRepository;

    public Raca findById(Long id) {
        Optional<Raca> raca = this.racaRepository.findById(id);
        return raca.orElseThrow(() -> new RuntimeException(
                "Classe não encontrada! Id: " + id + ", Tipo: " + Raca.class.getName()));
    }

    public Iterable<Raca> findAll(){
        Iterable<Raca> raca = this.racaRepository.findAll();
        return raca;
    }

    @Transactional
    public Raca create(Raca obj) {
        obj.setId(null);
        obj = this.racaRepository.save(obj);
        return obj;
    }

    public void delete(Long id) {
        findById(id);
        this.racaRepository.deleteById(id);
    }

}
