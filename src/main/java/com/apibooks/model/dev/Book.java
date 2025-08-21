package com.apibooks.model.dev;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id @GeneratedValue private Long id_livro;
	
	private String titulo;
	
	private String autor;
	
	private String genero;
	
	private String status;

	public Book() {}

	public Book(Long id_livro, String titulo, String autor, String genero, String status) {
		this.id_livro = id_livro;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.status = status;
	}

	public Long getId_livro() {
		return id_livro;
	}

	public void setId_livro(Long id_livro) {
		this.id_livro = id_livro;
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
		return Objects.hash(autor, genero, id_livro, status, titulo);
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
				&& Objects.equals(id_livro, other.id_livro) && Objects.equals(status, other.status)
				&& Objects.equals(titulo, other.titulo);
	}
}
