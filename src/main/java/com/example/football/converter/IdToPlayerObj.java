package com.example.football.converter;

import com.example.football.model.Player;
import com.example.football.service.PlayerService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * Custom controller (companyId -> to company object)
 * */
public class IdToPlayerObj implements org.springframework.core.convert.converter.Converter<Integer, Player>,
        com.fasterxml.jackson.databind.util.Converter<Integer, Player> {

    private final PlayerService playerService;

    @Autowired
    public IdToPlayerObj(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public Player convert(Integer source) {
        return playerService.get(source);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Integer.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Player.class);
    }

}