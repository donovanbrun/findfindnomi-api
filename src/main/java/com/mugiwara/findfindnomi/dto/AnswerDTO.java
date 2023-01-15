package com.mugiwara.findfindnomi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerDTO {

    private Long idDevilFruit;
    private Long idQuestion;
    private Long idCharacter;
    private String question;
    private int level;
    private String answer;
}
