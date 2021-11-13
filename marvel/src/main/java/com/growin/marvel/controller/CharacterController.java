package com.growin.marvel.controller;

import com.growin.marvel.service.HeroesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class CharacterController {

    private final HeroesService heroesService;

    @RequestMapping("/characters")
    public Object getAllTopics() {
        return heroesService.getAllCharacters();
    }

    @RequestMapping(value = "/characters/{id}", method = RequestMethod.GET)
    public Object getAllTopics(@PathVariable Integer id, @RequestParam("language") String locale) throws IOException {
        return  heroesService.findCharacter(id, locale);
    }
}
