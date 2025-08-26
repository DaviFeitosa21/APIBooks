package com.apibooks.dev.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apibooks.dev.exception.BookNotFoundException;
import com.apibooks.dev.model.dto.BookDTO;
import com.apibooks.dev.model.entity.Book;
import com.apibooks.dev.repository.BookRepository;


@Service
public class BookService {
	
	private final BookRepository repository;

	public BookService(BookRepository repository) {
		this.repository = repository;
	}
	
	public List<Book> all() {
		return repository.findAll();
	}
	
	public Book one(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new BookNotFoundException(id));
	}
	
	public Book newBook(Book book) {
		return repository.save(book);
	}
	
	public Book updateBook(Long id, BookDTO dto) {
		return repository.findById(id)
				.map(book -> {
					book.setTitulo(dto.getTitulo());
					book.setAutor(dto.getAutor());
			        book.setGenero(dto.getGenero());
			        book.setStatus(dto.getStatus());
			        return repository.save(book);
				})
				.orElseGet(() -> {
					Book newBook = new Book();
			        newBook.setId_livro(id);
			        newBook.setTitulo(dto.getTitulo());
			        newBook.setAutor(dto.getAutor());
			        newBook.setGenero(dto.getGenero());
			        newBook.setStatus(dto.getStatus());
					return repository.save(newBook);
				});
	}
	
	public void deleteBook(Long id) {
		repository.deleteById(id);
	}

}
