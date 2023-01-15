package com.mugiwara.findfindnomi.dto;

import com.mugiwara.findfindnomi.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuessResultDTO {

    private Long idDevilFruit;
    private String proposition;
    private boolean result;
    private Character character;
}
