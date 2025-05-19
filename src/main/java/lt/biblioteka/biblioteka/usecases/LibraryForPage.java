package lt.biblioteka.biblioteka.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import lt.biblioteka.biblioteka.interfaces.BookFormatter;
import lt.biblioteka.biblioteka.interfaces.LibraryNameFormatter;
import lt.biblioteka.biblioteka.interfaces.ReaderFormatter;
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


    @Inject
    @Getter
    private ReaderFormatter readerFormatter;

    @Inject
    @Getter
    private BookFormatter bookFormatter;

    private Library library;

    @Getter
    @Setter
    private Long readerToAddId;

    @Getter
    @Setter
    private Book bookToCreate = new Book();

    @Getter
    @Setter
    private Book selectedBook;


    @Transactional
    public void createBook(){
        bookToCreate.setLibrary(library);
        bookToCreate.setBorrowed(Boolean.FALSE);
        this.booksDAO.persist(bookToCreate);
    }

    @Transactional
    public void addReader(){
        Reader existingReader = this.readersDAO.findOne(readerToAddId);
        if (existingReader != null && !library.getReaders().contains(existingReader)) {
            library.getReaders().add(existingReader);  // Add the reader to the librarys reader list
            existingReader.getLibraries().add(library);  // Add the library to the readers library list

            this.librariesDAO.persist(library);  // Save the library with the updated reader list
            this.readersDAO.persist(existingReader);  // Save the reader with the updated library list
        } else {
            throw new IllegalArgumentException("Reader not found with the provided ID");
        }
    }
    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String libraryIdParam = requestParameters.get("libraryId");

        if (libraryIdParam != null && !libraryIdParam.isEmpty()) {
            try {
                Long libraryId = Long.parseLong(libraryIdParam);
                this.library = librariesDAO.findOne(libraryId);
                loadBooks();
            } catch (NumberFormatException e) {
                // Log or show a message: invalid libraryId format
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid library ID", null));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing library ID", null));
        }
    }

    public void loadBooks() {
        if (this.library != null) {
            this.library.setBooks(booksDAO.findBooksByLibraryId(this.library.getId()));
        }
    }

    public void toggleBorrowed(Book book) {
        try {
            booksDAO.toggleBorrowed(book.getId());
        } catch (RollbackException e) {
            Throwable cause = e.getCause();
            while (cause != null) {
                if (cause instanceof OptimisticLockException) {
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "The book was updated by another user. Please reload and try again.", null));
                    // Perkrauti knyga, leisti is naujo paspausti. Musu atveju kadanig po requesto refreshinas page, automatiskai buna perkrauta.
                    return;
                }
                cause = cause.getCause();
            }
        }

        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "The operation was interrupted.", null));
        }
    }





    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}