package com.apibooks.dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apibooks.dev.exception.BookNotFoundException;
import com.apibooks.dev.model.Book;
import com.apibooks.dev.repository.BookRepository;

@RestController
public class BookController {

	private final BookRepository repository;
	
	BookController(BookRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/books")
	List<Book> all() {
		return repository.findAll();
	}
	
	@GetMapping("/books/{id}")
	Book one(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new BookNotFoundException(id));
	}
	
	@PostMapping("/books")
	Book newBook(@RequestBody Book newBook) {
		return repository.save(newBook);
	}
	
	@PutMapping("/books/{id}")
	Book replaceBook(@RequestBody Book newBook,@PathVariable Long id) {
		return repository.findById(id)
				.map(book -> {
					book.setTitulo(newBook.getTitulo());
					book.setAutor(newBook.getAutor());
					book.setGenero(newBook.getGenero());
					book.setStatus(newBook.getStatus());
					return repository.save(book);
				})
				.orElseGet(() -> {
					return repository.save(newBook);
				});
	}
	
	@DeleteMapping("/books/{id}")
	void deleteBook(@PathVariable Long id) {
		repository.deleteById(id);
	}	
}
