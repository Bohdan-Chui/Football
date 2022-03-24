package com.example.football.config;

import com.example.football.model.Command;
import com.example.football.model.Player;
import com.example.football.repository.CommandRepository;
import com.example.football.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final CommandRepository commandRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public DataLoader(CommandRepository commandRepository, PlayerRepository playerRepository) {
        this.commandRepository = commandRepository;
        this.playerRepository = playerRepository;
    }

    public void run(ApplicationArguments args) {
        commandRepository.save(new Command("Bohdan", 3, 17.));
        commandRepository.save(new Command("DinamoKyiv", 2, 12657634.));
        commandRepository.save(new Command("Shakhtar", 3, 437.));

        playerRepository.save(new Player("Bohdan", 123, 17, commandRepository.getById(1)));
        playerRepository.save(new Player("Igor", 300, 23, commandRepository.getById(2)));
        playerRepository.save(new Player("John", 200, 27, commandRepository.getById(3)));
        playerRepository.save(new Player("Artur", 345, 30, commandRepository.getById(1)));
        playerRepository.save(new Player("Ben", 273, 29, commandRepository.getById(2)));
        playerRepository.save(new Player("Robert", 902, 40, commandRepository.getById(3)));

    }
}
