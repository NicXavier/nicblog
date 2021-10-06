package org.generation.blogPessoal.Services;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.ThemeModel;
import org.generation.blogPessoal.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ThemeServices {
	
	@Autowired
	private ThemeRepository repository;
		
	
	
}
