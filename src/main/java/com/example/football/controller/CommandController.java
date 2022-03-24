package com.example.football.controller;

import com.example.football.model.Command;
import com.example.football.service.CommandService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/command")
public class CommandController {

    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping("/save")
    public ResponseEntity<Command> saveCommand(@Valid @RequestBody Command command) {
        return ResponseEntity
                .ok()
                .body(commandService.save(command));
    }

    @PatchMapping("/update")
    public ResponseEntity<Command> updateCommand(@Valid @RequestBody Command command) {
        return ResponseEntity
                .ok()
                .body(commandService.update(command));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Command> getById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .ok()
                .body(commandService.get(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Integer id) {
        commandService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }
}
