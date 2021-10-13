package org.generation.blogPessoal.model.utilitymodels;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDTO{
	
	@NotBlank
	@Email
	private String email;
	
	private Long id;
	private String name;
	private String token;
	private String photo;
	private String typeUser;
	
	@NotBlank
	private String password;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}
	
	
}