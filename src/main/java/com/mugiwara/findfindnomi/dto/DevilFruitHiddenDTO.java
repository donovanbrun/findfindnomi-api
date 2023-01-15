package com.mugiwara.findfindnomi.dto;

import com.mugiwara.findfindnomi.entity.Character;
import com.mugiwara.findfindnomi.entity.DevilFruit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class DevilFruitHiddenDTO {

    private Long id;
    private String name;
    private String image;
    private Long characterId;
    //private List<QuestionDAO> questions;

    public DevilFruitHiddenDTO(DevilFruit devilFruitDAO, Character characterDAO) {
        if (devilFruitDAO != null) {
            this.id = devilFruitDAO.getId();
            this.name = devilFruitDAO.getName();
            this.image = devilFruitDAO.getImage();
        }
        if (characterDAO != null) {
            this.characterId = characterDAO.getId();
        }
    }
}
