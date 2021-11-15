package com.growin.marvel;

import com.growin.marvel.controller.CharacterController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class MarvelApplicationTests {

	@Autowired
	private CharacterController controller;

	@Test
	public void getAllCharactersSuccess() {
		ResponseEntity response = controller.getAllCharacters();
		Assert.assertEquals("200", String.valueOf(response.getStatusCode().value()));
	}

	@Test
	public void getCharacterByIdWithoutLanguageSuccess() {
		Integer id = 1009144;
		ResponseEntity response = controller.getAllCharacters(id, "");
		Assert.assertEquals("200", String.valueOf(response.getStatusCode().value()));
	}

	@Test
	public void getCharacterByIdWithLanguageSuccess() {
		Integer id = 1009144;
		ResponseEntity response = controller.getAllCharacters(id, "pt");
		Assert.assertEquals("200", String.valueOf(response.getStatusCode().value()));
	}

	@Test
	public void getCharacterByIdWithLanguageBadRequestError() {
		Integer id = 1009144;
		ResponseEntity response = controller.getAllCharacters(id, "gg");
		Assert.assertEquals("400", String.valueOf(response.getStatusCode().value()));
	}

	@Test
	public void getCharacterByIdWithLanguageIneternalServerError() {
		Integer id = 10;
		ResponseEntity response = controller.getAllCharacters(id, "en");
		Assert.assertEquals("500", String.valueOf(response.getStatusCode().value()));
	}

}
