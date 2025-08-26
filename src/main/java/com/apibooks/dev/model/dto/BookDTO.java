package com.apibooks.dev.model.dto;

public class BookDTO {
	
	private String titulo;
	private String autor;
	private String genero;
	private String status;
	
	public BookDTO() {}
	
	public BookDTO(String titulo, String autor, String genero, String status) {
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.status = status;
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
}
