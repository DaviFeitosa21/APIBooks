package com.apibooks.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apibooks.dev.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
