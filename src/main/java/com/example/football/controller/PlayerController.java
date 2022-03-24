package com.example.football.controller;

import com.example.football.dto.TransferDTO;
import com.example.football.model.Player;
import com.example.football.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/save")
    public ResponseEntity<Player> savePlayer(@Valid @RequestBody Player player) {
        return ResponseEntity
                .ok()
                .body(playerService.save(player));
    }

    @PatchMapping("/update")
    public ResponseEntity<Player> updatePlayer(@Valid @RequestBody Player player) {
        return ResponseEntity
                .ok()
                .body(playerService.update(player));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Player> getById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .ok()
                .body(playerService.get(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Integer id) {
        playerService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Player> transfer(@Valid @RequestBody TransferDTO transferDTO) {
        return ResponseEntity
                .ok(playerService.transfer(transferDTO));
    }
}