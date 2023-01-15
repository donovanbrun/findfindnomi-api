package com.mugiwara.findfindnomi.dto;

import com.mugiwara.findfindnomi.entity.DevilFruit;
import com.mugiwara.findfindnomi.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DevilFruitFullDTO {

    private Long id;
    private String name;
    private Type type;
    private String image;
    private List<CharacterDTO> characters;

    public DevilFruitFullDTO(DevilFruit devilFruitDAO, List<CharacterDTO> characters) {
        if (devilFruitDAO != null) {
            this.id = devilFruitDAO.getId();
            this.name = devilFruitDAO.getName();
            this.type = devilFruitDAO.getType();
            this.image = devilFruitDAO.getImage();
        }
        this.characters = characters;
    }
}
