package lt.biblioteka.biblioteka.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.biblioteka.biblioteka.interfaces.LibraryNameFormatter;
import lt.biblioteka.biblioteka.persistence.LibrariesDAO;
import lt.biblioteka.biblioteka.entities.Library;
import lt.biblioteka.biblioteka.services.LibraryService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Libraries {

    @Inject
    private LibrariesDAO librariesDAO;

    @Inject
    private LibraryService libraryService;

    @Inject
    private LibraryNameFormatter nameFormatter;

    @Getter @Setter
    private Integer selectedLibraryBookCount;

    @Getter @Setter
    private Library libraryToCreate = new Library();

    @Getter
    private List<Library> allLibraries;

//    public void fetchBookCount(Library selectedLibrary) {
//        if (selectedLibrary != null) {
//            // Fetch the book count and store it in the managed property
//            this.selectedLibraryBookCount = libraryService.getBookCount(selectedLibrary);
//        }
//        else {
//            throw new IllegalArgumentException("There is no such a library");
//        }
//    }

    @PostConstruct
    public void init(){
        loadAllLibraries();
    }

    @Transactional
    public void createLibrary(){
        this.librariesDAO.persist(libraryToCreate);
    }

    private void loadAllLibraries(){
        this.allLibraries = librariesDAO.loadAll();
    }

    public String getFormattedLibraryName(Library lib) {
        return nameFormatter.format(lib.getName());
    }
}
