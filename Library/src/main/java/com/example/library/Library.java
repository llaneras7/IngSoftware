package com.example.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una biblioteca que gestiona una colección de libros.
 */
public class Library {
    private List<Book> books; // Lista para almacenar los libros de la biblioteca.

    /**
     * Constructor que inicializa la biblioteca con una lista vacía de libros.
     */
    public Library() {
        this.books = new ArrayList<>();
    }

    /**
     * Añade un libro a la colección de la biblioteca.
     *
     * @param book El libro a añadir a la biblioteca.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Intenta eliminar un libro de la colección de la biblioteca.
     *
     * @param book El libro a eliminar.
     * @return true si el libro fue eliminado con éxito, de lo contrario false.
     */
    public boolean removeBook(String title, String author) {
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author) && book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                return true;
            }
        }
        return false;
    }


    /**
     * Busca libros en la biblioteca por el autor.
     *
     * @param author El autor por cuyos libros buscar.
     * @return Una lista de libros encontrados que coinciden con el autor dado.
     */
    public List<Book> findBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /**
     * Busca libros en la biblioteca por el titulo.
     *
     * @param title El título a buscar.
     * @return Una lista de libros encontrados que coinciden con el título dado.
     */
    public List<Book> findBooksByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /**
     * Cuenta el número total de libros en la biblioteca.
     *
     * @return El número total de libros disponibles en la biblioteca.
     */
    public int countBooks() {
        return books.size();
    }
}
