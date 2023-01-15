package com.mugiwara.findfindnomi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CharacterDTO {

    private Long id;
    private String name;
    private String image;
    private List<QuestionDTO> questions;

    public CharacterDTO(com.mugiwara.findfindnomi.entity.Character characterDAO, List<QuestionDTO> questions) {
        if (characterDAO != null) {
            this.id = characterDAO.getId();
            this.name = characterDAO.getName();
            this.image = characterDAO.getImage();
        }
        this.questions = questions;
    }
}
