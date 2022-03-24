package com.example.football.service;

import com.example.football.model.Command;
import com.example.football.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommandService {
    CommandRepository commandRepository;

    @Autowired
    public CommandService(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public Command save(Command command) {
        if (commandRepository.findByName(command.getName()).isPresent())
            throw new EntityExistsException("Command with this name is already present");
        return commandRepository.save(command);
    }

    public Command update(Command command) {
        if (command.getId() == null) {
            throw new IllegalArgumentException("Id == null");
        }
        if (!commandRepository.existsById(command.getId())) {
            throw new NoSuchElementException("No command in database with that id");
        }
        return commandRepository.save(command);
    }

    public Command get(Integer id) {
        if (!commandRepository.existsById(id))
            throw new NoSuchElementException("No command by that id");
        return Optional
                .of(commandRepository.getById(id))
                .orElseThrow(() -> new NoSuchElementException("empty element"));

    }

    public void deleteById(Integer id) {
        commandRepository.deleteById(id);
    }
}
