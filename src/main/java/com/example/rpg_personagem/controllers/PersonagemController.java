package com.example.rpg_personagem.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.rpg_personagem.models.Personagem;
import com.example.rpg_personagem.models.Personagem.CreatePersonagem;
import com.example.rpg_personagem.models.Personagem.UpdatePersonagem;
import com.example.rpg_personagem.services.PersonagemService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/personagem")
@Validated
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> findById(@PathVariable Long id) {
        Personagem obj = this.personagemService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Iterable<Personagem>> findAll() {
        Iterable<Personagem> personagens = this.personagemService.findAll();
        return ResponseEntity.ok().body(personagens);
    }

    @GetMapping("/usuario/{user_id}")
    public ResponseEntity<Iterable<Personagem>> findAllByUser(@PathVariable Long user_id) {
        Iterable<Personagem> personagens = this.personagemService.findAllByUserId(user_id);
        return ResponseEntity.ok().body(personagens);
    }

    @PostMapping
    @Validated(CreatePersonagem.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Personagem obj) {
        this.personagemService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdatePersonagem.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Personagem obj, @PathVariable Long id) {
        obj.setId(id);
        this.personagemService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.personagemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
