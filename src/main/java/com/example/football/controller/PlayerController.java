package com.example.football.controller;

import com.example.football.dto.PlayerDto;
import com.example.football.dto.TransferDto;
import com.example.football.model.Player;
import com.example.football.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerController(PlayerService playerService, ModelMapper modelMapper) {
        this.playerService = playerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<PlayerDto> savePlayer(@Valid @RequestBody PlayerDto playerDto) {
        Player player = modelMapper.map(playerDto, Player.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(playerService.save(player), PlayerDto.class));
    }

    @PatchMapping("/update")
    public ResponseEntity<PlayerDto> updatePlayer(@Valid @RequestBody PlayerDto playerDto) {
        Player player = modelMapper.map(playerDto, Player.class);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(modelMapper.map(playerService.update(player), PlayerDto.class));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PlayerDto> getById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .ok()
                .body(modelMapper.map(playerService.get(id), PlayerDto.class));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Integer id) {
        playerService.deleteById(id);
        return ResponseEntity
                .ok(HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<PlayerDto> transfer(@Valid @RequestBody TransferDto transferDTO) {
        Player player = playerService.transfer(transferDTO.getPlayerId(), transferDTO.getBuyerId());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(modelMapper.map(player, PlayerDto.class));
    }
}