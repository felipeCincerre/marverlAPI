package com.growin.marvel.client;

import com.growin.marvel.model.Heroe;
import com.growin.marvel.model.Heroes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="marvel-client", url="https://gateway.marvel.com:443/v1/public")
public interface MarvelClient {
    @RequestMapping(value="/characters", method = RequestMethod.GET)
    Heroes getAllCharacters(@RequestParam String ts, @RequestParam String apikey, @RequestParam String hash,
                            @RequestParam Integer limit, @RequestParam Integer offset);

    @RequestMapping(value="/characters/{id}", method = RequestMethod.GET)
    Heroe findCharacter(@PathVariable Integer id, @RequestParam String ts, @RequestParam String apikey,
                        @RequestParam String hash);
}
