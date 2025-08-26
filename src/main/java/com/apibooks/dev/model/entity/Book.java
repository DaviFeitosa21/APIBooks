package com.apibooks.dev.model.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id @GeneratedValue private Long id;
	
	private String titulo;
	
	private String autor;
	
	private String genero;
	
	private String status;

	public Book() {}

	public Book(Long id, String titulo, String autor, String genero, String status) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.status = status;
	}

	public Long getId_livro() {
		return id;
	}

	public void setId_livro(Long id_livro) {
		this.id = id_livro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, genero, id, status, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(genero, other.genero)
				&& Objects.equals(id, other.id) && Objects.equals(status, other.status)
				&& Objects.equals(titulo, other.titulo);
	}
}
