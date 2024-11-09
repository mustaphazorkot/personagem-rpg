package com.example.rpg_personagem.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.rpg_personagem.models.Raca;
import com.example.rpg_personagem.services.RacaService;

@RestController
@RequestMapping("/raca")
public class RacaController {

    @Autowired
    private RacaService racaService;

    @GetMapping("/{id}")
    public ResponseEntity<Raca> findById(@PathVariable Long id) {
        Raca obj = this.racaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Raca obj) {
        this.racaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.racaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
