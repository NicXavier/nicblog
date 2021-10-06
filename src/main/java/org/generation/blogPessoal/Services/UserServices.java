package org.generation.blogPessoal.Services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserModel;
import org.generation.blogPessoal.model.utilitymodels.UserDTO;
import org.generation.blogPessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices{
	
	@Autowired
	private UserRepository repository;
	
	public Optional<Object> registerUser(UserModel newUser){
		return repository.findByEmail(newUser.getEmail()).map(userExisting -> {
			return Optional.empty();
			}).orElseGet(() -> {
				
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode(newUser.getPassword());
		newUser.setPassword(result);
		return Optional.ofNullable(repository.save(newUser));
			});
	}


	public Optional<?> catchCredentials(UserDTO userToAuthenticate){
		return repository.findByEmail(userToAuthenticate.getEmail()).map(userExistent -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if (encoder.matches(userToAuthenticate.getPassword(), userExistent.getPassword())) {
				String structureBasic = userToAuthenticate.getEmail() + ":" + userToAuthenticate.getPassword();
				byte[] authorizationBase64 = Base64.encodeBase64(structureBasic.getBytes(Charset.forName("US-ASCII")));
				String authorizationHeader = "Basic " + new String (authorizationBase64);
			
				userToAuthenticate.setToken(authorizationHeader);
				userToAuthenticate.setId(userExistent.getId());
				userToAuthenticate.setName(userExistent.getName());
				userToAuthenticate.setPassword(userExistent.getPassword());
				return Optional.ofNullable(userToAuthenticate);
				
			}
			else
			{
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
		}
	}