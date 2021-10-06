package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.Services.UserServices;
import org.generation.blogPessoal.model.UserModel;
import org.generation.blogPessoal.model.utilitymodels.UserDTO;
import org.generation.blogPessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(tags = "UserController")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired 
	private UserServices services;
	
	@GetMapping
	public ResponseEntity<List<UserModel>> getAll(){
		List<UserModel> objectList = repository.findAll();
		
		if(objectList.isEmpty())
		{
			return ResponseEntity.status(204).build();
		} else
		{
			return ResponseEntity.status(200).body(objectList);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> post (@Valid @RequestBody UserModel newUser) {
		Optional<Object> objectOptional = services.registerUser(newUser);
		
		if (objectOptional.isEmpty())
		{
			return ResponseEntity.status(400).build();
		} else
		{
			return ResponseEntity.status(201).body(objectOptional.get());
		}
	}
	
	@PutMapping("/credentials")
	public ResponseEntity<Object> credentials(@Valid @RequestBody UserDTO userToAuthenticate ){
		Optional <?> objectOptional = services.catchCredentials(userToAuthenticate);
		
		if (objectOptional.isEmpty())
		{
			return ResponseEntity.status(400).build();
		} else
		{
			return ResponseEntity.status(201).body(objectOptional.get());
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> GetById (@PathVariable (value = "id") Long id){
		Optional <UserModel> objectUser = repository.findById(id);
		
		if (objectUser.isPresent())
		{
			return ResponseEntity.status(200).body(objectUser.get());
		} else
		{
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity <List<UserModel>> GetByNameI (@PathVariable (value = "name") String name){
		List<UserModel> objectList = repository.findAllByNameContainingIgnoreCase(name);
		
		if (objectList.isEmpty())
		{
			return ResponseEntity.status(204).build();
		} else
		{
			return ResponseEntity.status(200).body(objectList);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<UserModel>> GetByNameII (@RequestParam (defaultValue = "") String name){
		List<UserModel> objectList = repository.findAllByNameContainingIgnoreCase(name);
		
		if (objectList.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else 
		{
			return ResponseEntity.status(200).body(objectList);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserModel> put(@Valid @RequestBody UserModel updateUser){
		return ResponseEntity.status(201).body(repository.save(updateUser));
	}

	@DeleteMapping("/{id}")
	public void delete (@PathVariable (value = "id") long id) {
		repository.deleteById(id);
}
}
