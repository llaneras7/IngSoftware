package com.example.library;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LibraryManagerTest {
    private LibraryManager libraryManager;
    private Library library = new Library();
    @BeforeEach
    void setUp(){
        libraryManager = new LibraryManager(library);
    }
    @AfterEach
    void tearDown(){
        libraryManager = null;
    }
    @Test
    void shouldRemoveOnlyOneBookWithTitleAndReturnsTrue(){
        libraryManager.addNewBook("Topillo", "Azul", 1998);
        libraryManager.addNewBook("Topillo", "Perro", 1998);
        libraryManager.addNewBook("Aceituna", "Perro", 1998);
        assertTrue(libraryManager.removeBook("Topillo"));
        assertEquals(2, libraryManager.getTotalBooksInLibrary());
    }
    @Test
    void shouldBeEmptyAfterAddingAndRemoving1000BooksInLessThan100Miliseconds(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            libraryManager.addNewBook("Title" + i, "Author" + i, 2000 + i);
        }
        long insertionEndTime = System.currentTimeMillis();
        long insertionTime = insertionEndTime - startTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            libraryManager.removeBook("Title" + i);
        }
        long removalEndTime = System.currentTimeMillis();
        long removalTime = removalEndTime - startTime;

        assertEquals(0, libraryManager.getTotalBooksInLibrary(), "La biblioteca no está vacía después de eliminar todos los libros.");

        assertTrue(insertionTime < 100, "El tiempo de inserción excede los 100 milisegundos: " + insertionTime);
        assertTrue(removalTime < 100, "El tiempo de eliminación excede los 100 milisegundos: " + removalTime);
    }


}
