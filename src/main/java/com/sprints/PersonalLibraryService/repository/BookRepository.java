package com.sprints.PersonalLibraryService.repository;

import com.sprints.PersonalLibraryService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // the JPA ORM can be used to add queries without writing their implementation which will be unerstood by only parsing their names
    // the orm will generate a query like:
    // SELECT * FROM book WHERE author = ?;
    List<Book> findByAuthor(String author);


    //we can implement custom queries:
    @Query("SELECT b FROM Book b WHERE b.author = :author AND b.genre = :genre")
    List<Book> findByAuthorAndGenre(@Param("author") String author, @Param("genre") String genre);

}
