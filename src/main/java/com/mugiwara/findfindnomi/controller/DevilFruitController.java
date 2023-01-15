package com.mugiwara.findfindnomi.controller;

import com.mugiwara.findfindnomi.dto.AnswerDTO;
import com.mugiwara.findfindnomi.dto.DevilFruitFullDTO;
import com.mugiwara.findfindnomi.dto.DevilFruitHiddenDTO;
import com.mugiwara.findfindnomi.dto.GuessResultDTO;
import com.mugiwara.findfindnomi.dto.ingestion.IngestionModel;
import com.mugiwara.findfindnomi.service.DevilFruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class DevilFruitController {

    private DevilFruitService devilFruitService;

    @Autowired
    public DevilFruitController(DevilFruitService devilFruitService) {
        this.devilFruitService = devilFruitService;
    }

    @GetMapping("find/{id}")
    public DevilFruitFullDTO get(@PathVariable Long id) {
        return devilFruitService.get(id);
    }

    @GetMapping("random")
    public DevilFruitHiddenDTO getRandom() {
        return devilFruitService.getRandom();
    }

    @GetMapping("question")
    public AnswerDTO getAnswer(@RequestParam("idDevilFruit") Long idDevilFruit, @RequestParam("idCharacter") Long idCharacter, @RequestParam("idQuestion") Long idQuestion) {
        return devilFruitService.getAnswer(idDevilFruit, idCharacter, idQuestion);
    }

    @GetMapping("guess")
    public GuessResultDTO getAnswer(@RequestParam("idDevilFruit") Long idDevilFruit, @RequestParam("proposition") String proposition) {
        return devilFruitService.guess(idDevilFruit, proposition);
    }

    @PostMapping("load")
    public void load(@RequestBody IngestionModel ingestionModel) {
        devilFruitService.load(ingestionModel);
    }
}
