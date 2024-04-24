package com.example.library;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LibraryTest {
    private Library library;
    @BeforeEach
    void setUp(){
        this.library = new Library();
    }
    @AfterEach
    void tearDown(){
        library = null;
    }
    @Test
    void shouldHaveOneBookWhenABookIsAdded(){
        Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
        library.addBook(book);
        assertTrue(library.countBooks() == 1);
    }
    @Test
    void shouldFindOneBookByAuthorWhenABookByTheAuthorIsAdded(){
        Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
        library.addBook(book);
        List<Book> foundBooks = library.findBooksByAuthor("J.R.R. Tolkien");
        assertFalse(foundBooks.isEmpty());
        boolean foundBookByAuthor = false;
        for(Book foundBook : foundBooks){
            if (foundBook.getAuthor().equalsIgnoreCase("J.R.R. Tolkien")){
                foundBookByAuthor = true;
                break;
            }
        }
        assertTrue(foundBookByAuthor);
    }
    @Test
    void shouldDecreaseNumberOfBooksWhenABookIsRemoved(){
        Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
        library.addBook(book);
        int initialNumOfBooks = library.countBooks();
        boolean removalResult = library.removeBook("The Hobbit", "J.R.R. Tolkien");
        assertTrue(removalResult);
        int numOfBooksAfterRemove = library.countBooks();
        assertEquals(initialNumOfBooks - 1, numOfBooksAfterRemove);
    }
    @Test
    void shouldBeEmptyWhenLastBookIsRemoved(){
        Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
        library.addBook(book);
        library.removeBook("The Hobbit", "J.R.R. Tolkien");
        assertTrue(library.countBooks()==0);
    }


}
