package com.apibooks.dev.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apibooks.dev.assembler.BookModelAssembler;
import com.apibooks.dev.exception.BookNotFoundException;
import com.apibooks.dev.model.Book;
import com.apibooks.dev.repository.BookRepository;

@RestController
public class BookController {

	private final BookRepository repository;
	
	private final BookModelAssembler assembler;
	
	BookController(BookRepository repository, BookModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/books")
	public CollectionModel<EntityModel<Book>> all() {
		List<EntityModel<Book>> books = repository.findAll().stream()
			      .map(assembler::toModel)
			      .collect(Collectors.toList());

			  return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
	}
	
	@GetMapping("/books/{id}")
	public EntityModel<Book> one(@PathVariable Long id) {
		Book book = repository.findById(id)
				.orElseThrow(() -> new BookNotFoundException(id));

		return assembler.toModel(book);
	}
	
	@PostMapping("/books")
	ResponseEntity<?> newBook(@RequestBody Book newBook) {
		EntityModel<Book> bookModel = assembler.toModel(repository.save(newBook));

		  return ResponseEntity
		      .created(bookModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
		      .body(bookModel);
	}
	
	@PutMapping("/books/{id}")
	ResponseEntity<?> replaceBook(@RequestBody Book newBook,@PathVariable Long id) {
		Book updateBook = repository.findById(id)
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

			  EntityModel<Book> bookModel = assembler.toModel(updateBook);

			  return ResponseEntity
			      .created(bookModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			      .body(bookModel);
	}
	
	@DeleteMapping("/books/{id}")
	ResponseEntity<?> deleteBook(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}	
}
