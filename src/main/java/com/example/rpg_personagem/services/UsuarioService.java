package com.example.rpg_personagem.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rpg_personagem.models.Usuario;
import com.example.rpg_personagem.models.enuns.PerfilEnun;
import com.example.rpg_personagem.repositories.UsuarioRepository;
import com.example.rpg_personagem.services.exceptions.DataBidingViolationException;
import com.example.rpg_personagem.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Usuario findById(Long id) {
        Optional<Usuario> user = this.usuarioRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public Iterable<Usuario> findAll() {
        Iterable<Usuario> usuario = this.usuarioRepository.findAll();
        return usuario;
    }

    @Transactional
    public Usuario create(Usuario obj) {
        obj.setId(null);
        obj.setSenha(this.bCryptPasswordEncoder.encode(obj.getSenha()));
        obj.setPerfis(Stream.of(PerfilEnun.USER.getCode()).collect(Collectors.toSet()));
        obj = this.usuarioRepository.save(obj);
        return obj;
    }

    @Transactional
    public Usuario update(Usuario obj) {
        Usuario newObj = findById(obj.getId());
        newObj.setEmail(obj.getEmail());
        newObj.setSenha(this.bCryptPasswordEncoder.encode(obj.getSenha()));
        return this.usuarioRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBidingViolationException("Não é possível excluir pois exitem entidades relacionadas!");
        }
    }
}
