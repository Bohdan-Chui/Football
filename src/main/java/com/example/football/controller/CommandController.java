package com.example.football.controller;

import com.example.football.dto.CommandDto;
import com.example.football.model.Command;
import com.example.football.service.CommandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/command")
public class CommandController {

    private final CommandService commandService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommandController(CommandService commandService, ModelMapper modelMapper) {
        this.commandService = commandService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<CommandDto> saveCommand(@Valid @RequestBody CommandDto commandDto) {
        Command command = modelMapper.map(commandDto, Command.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(commandService.save(command), CommandDto.class));
    }

    @PatchMapping("/update")
    public ResponseEntity<CommandDto> updateCommand(@Valid @RequestBody CommandDto commandDto) {
        Command command = modelMapper.map(commandDto, Command.class);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(modelMapper.map(commandService.update(command), CommandDto.class));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CommandDto> getById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .ok()
                .body(modelMapper.map(commandService.get(id), CommandDto.class));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Integer id) {
        commandService.deleteById(id);
        return ResponseEntity
                .ok(HttpStatus.OK);

    }
}
