package com.example.football.repository;

import com.example.football.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandRepository extends JpaRepository<Command, Integer> {
    Optional<Command> findByName(String name);
}
