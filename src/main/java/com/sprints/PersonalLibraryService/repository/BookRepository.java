package com.sprints.PersonalLibraryService.repository;

import com.sprints.PersonalLibraryService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
