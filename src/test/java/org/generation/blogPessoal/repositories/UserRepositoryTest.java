package org.generation.blogPessoal.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.generation.blogPessoal.model.UserModel;
import org.generation.blogPessoal.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@BeforeEach
	void start() {
		UserModel obj1 = new UserModel("Hermione Glass", "hermione@gmail.com", "123456");
		if(!repository.findByEmail(obj1.getEmail()).isPresent())
			repository.save(obj1);
		
		UserModel obj2 = new UserModel("Harry Glass", "harry@gmail.com", "123456");
		if(!repository.findByEmail(obj2.getEmail()).isPresent())
			repository.save(obj2);
	}
	
	@Test
	void searchForExistingEmailReturnsTrue() {
		UserModel objHermione = repository.findByEmail("hermione@gmail.com").get();
		
		assertTrue(objHermione.getEmail().equals("hermione@gmail.com"));
	}
	
	@Test
	void findAllByNameContainingIgnoreCaseReturnsTwoUsers() {
		List<UserModel> objList = repository.findAllByNameContainingIgnoreCase("Glass");
		
		assertEquals(2, objList.size());
	}
	@AfterAll
	void end() {
		System.out.println("Teste finalizado!");
	}
	

}