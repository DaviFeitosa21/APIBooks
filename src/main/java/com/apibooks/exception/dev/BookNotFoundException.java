package com.apibooks.exception.dev;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(Long id) {
		super("Could not find Book " + id);
	}
}
