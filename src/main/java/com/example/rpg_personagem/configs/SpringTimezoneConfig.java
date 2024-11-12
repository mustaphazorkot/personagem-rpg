package com.example.rpg_personagem.configs;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

import java.util.TimeZone;

@Configuration
public class SpringTimezoneConfig {
    
    @PostConstruct
    public void timezoneConfig(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

}
