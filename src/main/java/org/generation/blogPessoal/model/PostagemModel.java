package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "posts")
public class PostagemModel {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String title;
	
	@Size(min = 10, max = 500)
	private String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JoinColumn(name = "theme_id")
	private ThemeModel theme;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel name;
		
	public ThemeModel getTheme() {
		return theme;
	}

	public void setTheme(ThemeModel theme) {
		this.theme = theme;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ThemeModel getTema() {
		return theme;
	}

	public void setTema(ThemeModel theme) {
		this.theme = theme;
	}

	public UserModel getName() {
		return name;
	}

	public void setName(UserModel name) {
		this.name = name;
	}
	
	
	
}
