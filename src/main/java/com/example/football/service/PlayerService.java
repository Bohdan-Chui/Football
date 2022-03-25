package com.example.football.service;

import com.example.football.dto.TransferDto;
import com.example.football.exception.NotEnoughMoney;
import com.example.football.model.Command;
import com.example.football.model.Player;
import com.example.football.repository.PlayerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlayerService {
    private static final int MULTIPLIER = 100_000;
    PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player save(Player player) {
        if (playerRepository.findByName(player.getName()).isPresent())
            throw new EntityExistsException("player with this name is already present");
        return playerRepository.save(player);
    }

    public Player update(Player player) {
        if (player.getId() == null) {
            throw new IllegalArgumentException("Id == null");
        }
        if (!playerRepository.existsById(player.getId())) {
            throw new NoSuchElementException("No player in database with that id");
        }
        return playerRepository.save(player);
    }

    public Player get(Integer id) {
        if (!playerRepository.existsById(id))
            throw new NoSuchElementException("No player by that id");
        return Optional
                .of(playerRepository.getById(id))
                .orElseThrow(() -> new NoSuchElementException("empty element"));

    }

    public void deleteById(Integer id) {
        playerRepository.deleteById(id);
    }

    public Player transfer(@NonNull Player player, @NonNull Command buyer) {

        Command seller = player.getCommand();

        if (seller.equals(buyer))
            throw new IllegalArgumentException("The buyer and seller are same");

        Double deal = Double.valueOf(player.getExperience()) * MULTIPLIER / Double.valueOf(player.getAge());

        if (buyer.getAccount() < deal)
            throw new NotEnoughMoney("Not enough money at buyer command");

        buyer.setAccount(buyer.getAccount() - deal);
        seller.setAccount(seller.getAccount() + deal);

        player.setCommand(buyer);
        return playerRepository.save(player);
    }
}
