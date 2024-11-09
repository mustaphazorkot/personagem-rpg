package com.example.rpg_personagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rpg_personagem.models.Usuario;
import com.example.rpg_personagem.models.Classe;
import com.example.rpg_personagem.models.Personagem;
import com.example.rpg_personagem.models.Raca;
import com.example.rpg_personagem.repositories.PersonagemRepository;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RacaService racaService;
    @Autowired
    private ClasseService classeService;

    public Personagem findById(Long id) {
        Optional<Personagem> personagem = this.personagemRepository.findById(id);
        return personagem.orElseThrow(() -> new RuntimeException(
                "Personagem não encontrado! Id:" + id + ", Tipo: " + Personagem.class.getName()));
    }

    @Transactional
    public Personagem create(Personagem obj) {
        Usuario usuario = this.usuarioService.findById(obj.getUsuario().getId());
        Classe classe = this.classeService.findById(obj.getClasse().getId());
        Raca raca = this.racaService.findById(obj.getRaca().getId());
        obj.setId(null);
        obj.setUsuario(usuario);
        obj.setClasse(classe);
        obj.setRaca(raca);
        obj = this.personagemRepository.save(obj);
        return obj;

    }

    @Transactional
    public Personagem update(Personagem obj) {
        Personagem newObj = findById(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setForca(obj.getForca());
        newObj.setDestreza(obj.getDestreza());
        newObj.setConstituicao(obj.getConstituicao());
        newObj.setInteligencia(obj.getInteligencia());
        newObj.setSabedoria(obj.getSabedoria());
        newObj.setCarisma(obj.getCarisma());
        newObj = this.personagemRepository.save(newObj);
        return newObj;
    }

    public void delete(Long id) {
        findById(id);
        this.personagemRepository.deleteById(id);
    }
}
