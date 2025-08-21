package com.apibooks.dev.exception;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(Long id) {
		super("Could not find Book " + id);
	}
}
