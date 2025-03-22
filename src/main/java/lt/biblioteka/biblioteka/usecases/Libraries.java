package lt.biblioteka.biblioteka.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.biblioteka.biblioteka.persistence.LibrariesDAO;
import lt.biblioteka.biblioteka.entities.Library;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Libraries {

    @Inject
    private LibrariesDAO librariesDAO;

    @Getter @Setter
    private Library libraryToCreate = new Library();

    @Getter
    private List<Library> allLibraries;

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
}
