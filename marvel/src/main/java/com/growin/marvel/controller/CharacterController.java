package com.growin.marvel.controller;

import com.growin.marvel.model.entities.Error;
import com.growin.marvel.service.HeroesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Locale;

@RestController
@AllArgsConstructor
public class CharacterController {

    private final HeroesService heroesService;

    @RequestMapping(value = "/characters", method = RequestMethod.GET)
    public ResponseEntity getAllCharacters() {
        return ResponseEntity.status(HttpStatus.OK).body(heroesService.getAllCharacters());
    }

    @RequestMapping(value = "/characters/{id}", method = RequestMethod.GET)
    public ResponseEntity getAllCharacters(@PathVariable Integer id,
                                       @RequestParam(value = "language", required = false) String locale) {
        if (isNotISOLanguage(locale)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(String.valueOf(
                    HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST.getReasonPhrase()+
                    " - Wrong value for parameter language."));
        }
        return  ResponseEntity.status(HttpStatus.OK).body(heroesService.findCharacter(id, locale));
    }

    private boolean isNotISOLanguage(String locale) {
        if(!StringUtils.isEmpty(locale)) {
            if (!Arrays.asList(Locale.getISOLanguages()).contains(locale.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
