package com.mugiwara.findfindnomi.entity;

import com.mugiwara.findfindnomi.dao.DevilFruitDAO;
import com.mugiwara.findfindnomi.dao.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DevilFruitFull {

    private Long id;
    private String name;
    private Type type;
    private String image;
    private List<Character> characters;

    public DevilFruitFull(DevilFruitDAO devilFruitDAO, List<Character> characters) {
        if (devilFruitDAO != null) {
            this.id = devilFruitDAO.getId();
            this.name = devilFruitDAO.getName();
            this.type = devilFruitDAO.getType();
            this.image = devilFruitDAO.getImage();
        }
        this.characters = characters;
    }
}
