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
import com.apibooks.dev.model.dto.BookDTO;
import com.apibooks.dev.model.entity.Book;
import com.apibooks.dev.service.BookService;

@RestController
public class BookController {

	private final BookService service;
	
	private final BookModelAssembler assembler;
	
	BookController(BookService service, BookModelAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}
	
	@GetMapping("/books")
	public CollectionModel<EntityModel<Book>> all() {
		List<EntityModel<Book>> books = service.all().stream()
			      .map(assembler::toModel)
			      .collect(Collectors.toList());

		return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
	}
	
	@GetMapping("/books/{id}")
	public EntityModel<Book> one(@PathVariable Long id) {
		return assembler.toModel(service.one(id));
	}
	
	@PostMapping("/books")
	ResponseEntity<?> newBook(@RequestBody BookDTO dto) {
		Book book = new Book(null, dto.getTitulo(), dto.getAutor(), dto.getGenero(), dto.getStatus());
		Book savedBook = service.newBook(book);
		
		EntityModel<Book> bookModel = assembler.toModel(savedBook);

		return ResponseEntity
				.created(bookModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(bookModel);
	}
	
	@PutMapping("/books/{id}")
	ResponseEntity<?> updateBook(@RequestBody BookDTO dto,@PathVariable Long id) {
		Book updateBook = service.updateBook(id, dto);
		EntityModel<Book> bookModel = assembler.toModel(updateBook);

		return ResponseEntity
				.created(bookModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(bookModel);
	}
	
	@DeleteMapping("/books/{id}")
	ResponseEntity<?> deleteBook(@PathVariable Long id) {
		service.deleteBook(id);
		
		return ResponseEntity.noContent().build();
	}	
}
