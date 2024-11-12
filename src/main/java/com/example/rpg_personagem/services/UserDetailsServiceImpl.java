package com.example.rpg_personagem.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.rpg_personagem.models.Usuario;
import com.example.rpg_personagem.repositories.UsuarioRepository;
import com.example.rpg_personagem.security.UserSpringSecurity;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String apelido) throws UsernameNotFoundException {

        Usuario usuario = this.usuarioRepository.findByApelido(apelido);
        if(Objects.isNull(usuario)){
            throw new UsernameNotFoundException("Usuario não encontrado: " + apelido);
        }
       return new UserSpringSecurity(usuario.getId(), usuario.getNome(), usuario.getApelido(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
    }

}
