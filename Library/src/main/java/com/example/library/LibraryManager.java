package com.example.library;

/**
 * Gestiona las operaciones de la biblioteca, facilitando la adición y eliminación de libros,
 * así como la consulta del número total de libros.
 */
public class LibraryManager {
    private Library library; // La biblioteca que este gestor administra.

    public Library getLibrary(){
        return this.library;
    }
    /**
     * Constructor que inicializa el gestor de la biblioteca con una biblioteca existente.
     *
     * @param library La instancia de la biblioteca que será gestionada.
     */
    public LibraryManager(Library library) {
        this.library = library;
    }

    /**
     * Añade un nuevo libro a la biblioteca utilizando los parámetros dados para crear el libro.
     *
     * @param title El título del libro.
     * @param author El autor del libro.
     * @param year El año de publicación del libro.
     */
    public void addNewBook(String title, String author, int year) {
        Book book = new Book(title, author, year); // Crea una nueva instancia de libro.
        library.addBook(book); // Añade el libro a la biblioteca.
    }

    /**
     * Intenta eliminar un libro de la biblioteca basándose en el título.
     * Si hay libros de varios autores con el mismo título, eliminará el primero que encuentre.
     *
     * @param title El título del libro a eliminar.
     * @return true si el libro se eliminó con éxito, false si el libro no se encontró.
     */
    public boolean removeBook(String title) {
        for (Book book : library.findBooksByTitle(title)) { // Busca todos los libros que coincidan con el autor dado.
            if (book.getTitle().equalsIgnoreCase(title)) { // Comprueba si el título coincide.
                return library.removeBook(book.getTitle(),book.getAuthor()); // Elimina el libro si se encuentra una coincidencia.
            }
        }
        return false; // Retorna falso si no se encuentra el libro para eliminar.
    }

    /**
     * Devuelve el número total de libros disponibles en la biblioteca.
     *
     * @return El número total de libros en la biblioteca.
     */
    public int getTotalBooksInLibrary() {
        return library.countBooks(); // Consulta y retorna el número total de libros.
    }
}
