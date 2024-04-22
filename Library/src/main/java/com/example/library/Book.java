package com.example.library;

/**
 * Representa un libro con título, autor y año de publicación.
 * Esta clase proporciona métodos para obtener la información del libro.
 */
public class Book {
    private String title; // Título del libro
    private String author; // Autor del libro
    private int yearPublished; // Año de publicación del libro

    /**
     * Constructor que inicializa un nuevo libro con los detalles proporcionados.
     *
     * @param title Título del libro.
     * @param author Autor del libro.
     * @param yearPublished Año en que el libro fue publicado.
     */
    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    /**
     * Devuelve el título del libro.
     *
     * @return El título del libro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Devuelve el autor del libro.
     *
     * @return El autor del libro.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Devuelve el año de publicación del libro.
     *
     * @return El año de publicación del libro.
     */
    public int getYearPublished() {
        return yearPublished;
    }
}
