package com.apibooks.repository.dev;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apibooks.model.dev.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
