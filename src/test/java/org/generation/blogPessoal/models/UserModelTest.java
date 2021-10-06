package org.generation.blogPessoal.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.generation.blogPessoal.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserTest {

	public UserModel user;

	@Autowired
	public ValidatorFactory factory  = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		user = new UserModel("Nicole Xavier", "nclxvr@gmail.com", "123456");
			}
	@Test
	void validateCorrectUserReturnsTrue() {
		Set<ConstraintViolation<UserModel>> violationObj = validator.validate(user);
		assertTrue (violationObj.isEmpty());
	}
	
	@Test
	void validateWrongUserReturnsFalse() {
		UserModel failedUser = new UserModel("", "bruce@gmail.com", "123456");
		Set<ConstraintViolation<UserModel>> violationObj = validator.validate(failedUser);
		assertFalse(violationObj.isEmpty());
	}




}
