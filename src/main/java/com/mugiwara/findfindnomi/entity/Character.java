package com.mugiwara.findfindnomi.entity;

import com.mugiwara.findfindnomi.dao.CharacterDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Character {

    private Long id;
    private String name;
    private String image;
    private List<Question> questions;

    public Character(CharacterDAO characterDAO, List<Question> questions) {
        if (characterDAO != null) {
            this.id = characterDAO.getId();
            this.name = characterDAO.getName();
            this.image = characterDAO.getImage();
        }
        this.questions = questions;
    }
}
