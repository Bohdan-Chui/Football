package com.example.football.converter;

import com.example.football.model.Command;
import com.example.football.service.CommandService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * Custom controller (companyId -> to company object)
 * */
public class IdToCommandObj implements org.springframework.core.convert.converter.Converter<Integer, Command>,
        com.fasterxml.jackson.databind.util.Converter<Integer, Command> {

    private final CommandService commandService;

    @Autowired
    public IdToCommandObj(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public Command convert(Integer source) {
        return commandService.get(source);
    }
    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Integer.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Command.class);
    }

}