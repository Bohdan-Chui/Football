package com.example.football.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "command_id")
    private Integer id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "commission")
    @Min(0)
    @Max(10)
    private Integer commission;

    @Column(name = "account")
    @Min(0)
    private Double account;

    @JsonIgnore
    @OneToMany(mappedBy = "command")
    private List<Player> players;

    public Command(String name, Integer commission, Double account) {
        this.name = name;
        this.commission = commission;
        this.account = account;
    }

}
