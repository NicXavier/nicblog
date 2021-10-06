package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.generation.blogPessoal.model.PostagemModel;
import org.generation.blogPessoal.repository.PostagemRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
@Api(tags = "PostController")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@GetMapping 
	public ResponseEntity<List<PostagemModel>> getAll(){
		List<PostagemModel> objetoLista = repository.findAll();
		
		if (objetoLista.isEmpty()) {
			
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<PostagemModel> post (@Valid @RequestBody PostagemModel  newPost){
		return ResponseEntity.status(201).body(repository.save(newPost));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostagemModel> GetByID(@PathVariable (value = "id") Long idPost) {
		Optional<PostagemModel> objetoPostagemModel = repository.findById(idPost);
		 	
		if (objetoPostagemModel.isPresent()) {
			
			return ResponseEntity.status(200).body(objetoPostagemModel.get());	
		} else {
			return ResponseEntity.status(204).build();		}
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<List<PostagemModel>> GetByTitle(@PathVariable (value = "title") String title){
		List<PostagemModel> objetoLista = repository.findAllByTitleContainingIgnoreCase(title);
		
		if (objetoLista.isEmpty()) {
			
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/search")
	public ResponseEntity<List<PostagemModel>> post(@RequestParam(value = "text") String text) {
		List<PostagemModel> objetoLista = repository.findAllByTextContainingIgnoreCase(text);

		if (objetoLista.isEmpty()) {

			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<PostagemModel> put(@RequestBody PostagemModel postagem) {
		return ResponseEntity.status(201).body(repository.save(postagem));

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
