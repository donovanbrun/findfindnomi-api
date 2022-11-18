package com.mugiwara.findfindnomi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Answer {

    private Long idDevilFruit;
    private Long idQuestion;
    private Long idCharacter;
    private String question;
    private int level;
    private String answer;
}
