package com.example.rpg_personagem.models.enuns;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PerfilEnun {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private Integer code;
    private String description;

    public static PerfilEnun toEnun(Integer code) {

        if (Objects.isNull(code)) {
            return null;
        }
        for (PerfilEnun x : PerfilEnun.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);

    }
}
