package com.example.football.dto;

import com.example.football.converter.IdToCommandObj;
import com.example.football.converter.IdToPlayerObj;
import com.example.football.model.Command;
import com.example.football.model.Player;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {

    @JsonDeserialize(converter = IdToPlayerObj.class)
    private Player playerId;

    @JsonDeserialize(converter = IdToCommandObj.class)
    private Command buyerId;

}
