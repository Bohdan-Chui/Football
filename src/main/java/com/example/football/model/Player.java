package com.example.football.model;

import com.example.football.converter.IdToCommandObj;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "experience")
    @Min(0)
    private Integer experience;

    @Column(name = "age")
    @Min(2)
    @Max(70)
    private Integer age;

    @JsonDeserialize(converter = IdToCommandObj.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "command_id")
    private Command command;

    public Player(String name, Integer experience, Integer age, Command command) {
        this.name = name;
        this.experience = experience;
        this.age = age;
        this.command = command;
    }
}
