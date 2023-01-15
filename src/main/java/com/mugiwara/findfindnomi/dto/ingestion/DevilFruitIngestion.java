package com.mugiwara.findfindnomi.dto.ingestion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DevilFruitIngestion {

    private String name;
    private String type;
    private String image;
    private List<CharacterIngestion> characters;
}
