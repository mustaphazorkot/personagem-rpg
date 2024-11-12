package com.example.rpg_personagem.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.rpg_personagem.models.enuns.PerfilEnun;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSpringSecurity implements UserDetails {

    private Long id;
    private String apelido;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(Long id, String apelido, String senha, Set<PerfilEnun> perfilEnun) {
        this.id = id;
        this.apelido = apelido;
        this.senha = senha;
        this.authorities = perfilEnun.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

    public boolean hasRole(PerfilEnun perfilEnun){
        return getAuthorities().contains(new SimpleGrantedAuthority(perfilEnun.getDescription()));
    }
}
