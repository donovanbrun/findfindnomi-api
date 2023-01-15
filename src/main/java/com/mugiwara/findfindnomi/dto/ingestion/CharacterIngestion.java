package com.mugiwara.findfindnomi.dto.ingestion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CharacterIngestion {

    private String name;
    private String image;
    private AnswerIngestion[] answers;
}
