package com.apibooks.dev.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.apibooks.dev.controller.BookController;
import com.apibooks.dev.model.entity.Book;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {

	@Override
	public EntityModel<Book> toModel(Book book) {
		
		return EntityModel.of(book,
				linkTo(methodOn(BookController.class).one(book.getId_livro())).withSelfRel(),
				linkTo(methodOn(BookController.class).all()).withRel("books"));
	}
}
