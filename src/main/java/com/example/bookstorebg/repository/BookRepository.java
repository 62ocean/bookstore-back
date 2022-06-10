package com.example.bookstorebg.repository;

import com.example.bookstorebg.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

}
