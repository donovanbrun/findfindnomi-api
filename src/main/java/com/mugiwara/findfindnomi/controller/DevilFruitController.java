package com.mugiwara.findfindnomi.controller;

import com.mugiwara.findfindnomi.entity.DevilFruitFull;
import com.mugiwara.findfindnomi.entity.DevilFruitHidden;
import com.mugiwara.findfindnomi.service.DevilFruitService;
import org.hibernate.cfg.NotYetImplementedException;
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
    public DevilFruitFull get(@PathVariable Long id) {
        return this.devilFruitService.get(id);
    }

    @GetMapping("random")
    public DevilFruitHidden getRandom() {
        return this.devilFruitService.getRandom();
    }

    @GetMapping("question")
    public void getAnswer(@RequestParam("idDevilFruit") Long idDevilFruit, @RequestParam("idCharacter") Long idCharacter, @RequestParam("idQuestion") Long idQuestion) {
        throw new NotYetImplementedException();
    }

    @GetMapping("guess")
    public void getAnswer(@RequestParam("idDevilFruit") Long idDevilFruit, @RequestParam("proposition") String proposition) {
        throw new NotYetImplementedException();
    }
}
