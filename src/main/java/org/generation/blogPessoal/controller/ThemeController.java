package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.ThemeModel;
import org.generation.blogPessoal.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/theme")
@Api(tags = "ThemeController")
public class ThemeController {
	
	@Autowired 
	private ThemeRepository repository;

	
	@GetMapping
	public ResponseEntity<List<ThemeModel>> getAll(){
		List<ThemeModel> objectList = repository.findAll();
		
		if (objectList.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else
		{
			return ResponseEntity.status(200).body(objectList);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ThemeModel> getById(@PathVariable (value = "id") Long id){
		Optional<ThemeModel> objectList = repository.findById(id);
		
		if (objectList.isPresent()) {
			return ResponseEntity.status(200).body(objectList.get());
		} else
		{
			return ResponseEntity.status(200).build();
		}
		
	}
	
	@GetMapping("/description/{description}")
	public ResponseEntity<List<ThemeModel>> getByDescription(@PathVariable (value = "description") String description){
		List<ThemeModel> objectList = (repository.findAllByDescriptionContainingIgnoreCase(description));
		
		if(objectList.isEmpty()) 
		{
			return ResponseEntity.status(204).build();
		} else
		{
			return ResponseEntity.status(200).body(objectList);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<ThemeModel> post (@Valid @RequestBody ThemeModel theme){
		return ResponseEntity.status(201).body(repository.save(theme));
	}
	
	@PutMapping
	public ResponseEntity<ThemeModel> put (@Valid @RequestBody ThemeModel theme){
		return ResponseEntity.status(201).body(repository.save(theme));				
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable (value = "id") long id) {
		repository.deleteById(id);
	}
}
