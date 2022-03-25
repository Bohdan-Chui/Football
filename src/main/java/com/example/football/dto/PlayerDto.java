package com.example.football.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PlayerDto {

    @JsonProperty("id")
    private Integer id;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @Min(0)
    @JsonProperty("experience")
    private Integer experience;

    @Min(2)
    @Max(70)
    @JsonProperty("age")
    private Integer age;
}
