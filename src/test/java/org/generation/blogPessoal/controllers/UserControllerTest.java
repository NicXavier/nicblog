package org.generation.blogPessoal.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.generation.blogPessoal.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	private UserModel createUser;
	private UserModel alterUser;
	
	@BeforeEach
	void start() {
		createUser = new UserModel ("Alan Turing", "alan@gmail.com","123456");
		alterUser = new UserModel ("Ada Turing", "ada@gmail.com","123456");
	}
	
		@Disabled
	@Test
	void saveNewUserInBankReturnStatus201() {
		HttpEntity<UserModel> requisicao = new HttpEntity<UserModel>(createUser);
		ResponseEntity<UserModel> response = testRestTemplate.exchange("/user/save",
				HttpMethod.POST, requisicao, UserModel.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Disabled
	@Test
	void findAllReturnStatus200() {
		
		ResponseEntity<String> response = testRestTemplate.withBasicAuth("alan@gmail.com","123456")
				.exchange("/user", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Disabled
	@Test
	void updateUserInBankReturnStatus201() {
		HttpEntity<UserModel> requisicao = new HttpEntity<UserModel>(alterUser);
		ResponseEntity<UserModel> response = testRestTemplate.withBasicAuth("ada@gmail.com","123456")
				.exchange("/user/credentials", HttpMethod.PUT, requisicao, UserModel.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}

