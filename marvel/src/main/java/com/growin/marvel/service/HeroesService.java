package com.growin.marvel.service;

import com.darkprograms.speech.translator.GoogleTranslate;
import com.growin.marvel.client.MarvelClient;
import com.growin.marvel.model.Heroes;
import com.growin.marvel.model.entities.Character;
import com.growin.marvel.model.entities.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeroesService {

    private final MarvelClient marvelClient;
    private final String authTs;
    private final String authPublicKey;
    private final String authHash;
    private Collection<Integer> ids;

    @Value("${api.limit}")
    private Integer limit;

    private Integer offset = 0;

    public Collection<Integer> getAllCharacters(){
        ids = new ArrayList<>();
        Heroes firstResult = marvelClient.getAllCharacters(authTs, authPublicKey, authHash, limit, offset);
        ids.addAll(firstResult.getData().getResults().stream().map(Character::getId).collect(Collectors.toList()));

        for (offset = offset + limit; offset < firstResult.getData().getTotal(); offset+=limit) {
            Heroes result = marvelClient.getAllCharacters(authTs, authPublicKey, authHash, limit, offset);
            ids.addAll(result.getData().getResults().stream().map(Character::getId).collect(Collectors.toList()));
        }
        return ids;
    }

    public Object findCharacter(Integer id, String locale){
        try {
            Optional<Character> result = marvelClient.findCharacter(id, authTs, authPublicKey,
                    authHash).getData().getResults().stream().findFirst();

            if(!StringUtils.isEmpty(locale)){
                String translation = GoogleTranslate.translate(locale, result.get().getDescription());
                result.get().setDescription(translation);
            }
            return result;
        }catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(String.valueOf(
                    HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                    +" - " + e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(String.valueOf(
                    HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                    + " - " + e.getMessage()));
        }
    }

}
