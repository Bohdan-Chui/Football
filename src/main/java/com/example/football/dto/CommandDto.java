package com.example.football.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CommandDto {

    @JsonProperty("id")
    private Integer id;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @Min(0)
    @Max(10)
    @JsonProperty("commission")
    private Integer commission;

    @Min(0)
    @JsonProperty("account")
    private Double account;

}
