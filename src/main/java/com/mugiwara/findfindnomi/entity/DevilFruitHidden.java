package com.mugiwara.findfindnomi.entity;

import com.mugiwara.findfindnomi.dao.CharacterDAO;
import com.mugiwara.findfindnomi.dao.DevilFruitDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class DevilFruitHidden {

    private Long id;
    private String name;
    private String image;
    private Long characterId;
    //private List<QuestionDAO> questions;

    public DevilFruitHidden(DevilFruitDAO devilFruitDAO, CharacterDAO characterDAO) {
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
