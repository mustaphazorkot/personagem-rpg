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
import com.example.rpg_personagem.services.exceptions.ObjectNotFoundException;

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
        return personagem.orElseThrow(() -> new ObjectNotFoundException(
                "Personagem não encontrado! Id:" + id + ", Tipo: " + Personagem.class.getName()));
    }

    public Iterable<Personagem> findAll(){
        Iterable<Personagem> personagem = this.personagemRepository.findAll();
        return personagem;
    }

    public Iterable<Personagem> findAllByUserId(Long userId){
        Iterable<Personagem> personagens = this.personagemRepository.findByUsuarioId(userId);
        return personagens;
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

        obj.setForca(obj.getForca() != null ? obj.getForca() : 0);
        obj.setDestreza(obj.getDestreza() != null ? obj.getDestreza() : 0);
        obj.setConstituicao(obj.getConstituicao() != null ? obj.getConstituicao() : 0);
        obj.setInteligencia(obj.getInteligencia() != null ? obj.getInteligencia() : 0);
        obj.setSabedoria(obj.getSabedoria() != null ? obj.getSabedoria() : 0);
        obj.setCarisma(obj.getCarisma() != null ? obj.getCarisma() : 0);

        obj = this.personagemRepository.save(obj);
        return obj;

    }

    @Transactional
    public Personagem update(Personagem obj) {
        Personagem newObj = findById(obj.getId());

        if (obj.getNome() == null) {
            newObj.setNome(obj.getNome());
        }

        if (obj.getForca() != null) {
            newObj.setForca(obj.getForca());
        }

        if (obj.getDestreza() != null) {
            newObj.setDestreza(obj.getDestreza());
        }

        if (obj.getConstituicao() != null) {
            newObj.setConstituicao(obj.getConstituicao());
        }

        if (obj.getInteligencia() != null) {
            newObj.setInteligencia(obj.getInteligencia());
        }

        if (obj.getSabedoria() != null) {
            newObj.setSabedoria(obj.getSabedoria());
        }

        if (obj.getCarisma() != null) {
            newObj.setCarisma(obj.getCarisma());
        }
        newObj = this.personagemRepository.save(newObj);
        return newObj;
    }

    public void delete(Long id) {
        findById(id);
        this.personagemRepository.deleteById(id);
    }
}
