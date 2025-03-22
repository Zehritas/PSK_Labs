package lt.biblioteka.biblioteka.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import lt.biblioteka.biblioteka.persistence.LibrariesDAO;
import lt.biblioteka.biblioteka.persistence.BooksDAO;
import lt.biblioteka.biblioteka.persistence.ReadersDAO;
import lt.biblioteka.biblioteka.entities.Library;
import lt.biblioteka.biblioteka.entities.Book;
import lt.biblioteka.biblioteka.entities.Reader;
import java.util.Map;

@Model
public class LibraryForPage {

    @Inject
    private LibrariesDAO librariesDAO;

    @Inject
    private BooksDAO booksDAO;

    @Inject
    private ReadersDAO readersDAO;

    private Library library;

    @Getter
    @Setter
    private Long readerToAddId;

    @Getter
    @Setter
    private Book bookToCreate = new Book();


    @Transactional
    public void createBook(){
        bookToCreate.setLibrary(library);
        this.booksDAO.persist(bookToCreate);
    }

    @Transactional
    public void addReader(){
        Reader existingReader = this.readersDAO.findOne(readerToAddId);
        if (existingReader != null && !library.getReaders().contains(existingReader)) {
            library.getReaders().add(existingReader);  // Add the reader to the library's reader list
            existingReader.getLibraries().add(library);  // Add the library to the reader's library list

            this.librariesDAO.persist(library);  // Save the library with the updated reader list
            this.readersDAO.persist(existingReader);  // Save the reader with the updated library list
        } else {
            // Handle case where the reader is not found (e.g., throw an exception or show an error message)
            throw new IllegalArgumentException("Reader not found with the provided ID");
        }
    }
    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long libraryId = Long.parseLong(requestParameters.get("libraryId"));
        this.library = librariesDAO.findOne(libraryId);  // Fetch the library based on ID
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}