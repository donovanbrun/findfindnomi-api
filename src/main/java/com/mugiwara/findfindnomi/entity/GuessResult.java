package com.mugiwara.findfindnomi.entity;

import com.mugiwara.findfindnomi.dao.CharacterDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GuessResult {

    private Long idDevilFruit;
    private String proposition;
    private boolean result;
    private CharacterDAO character;
}
