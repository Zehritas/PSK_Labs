package lt.biblioteka.biblioteka.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.biblioteka.biblioteka.mybatis.dao.LibraryMapper;
import lt.biblioteka.biblioteka.mybatis.model.Library;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@RequestScoped
public class LibrariesMyBatis {
    @Inject
    private LibraryMapper libraryMapper;

    @Getter
    private List<Library> allLibraries;

    @Getter @Setter
    private Library libraryToCreate = new Library();

    @PostConstruct
    public void init() {
        this.loadAllLibraries();
    }

    private void loadAllLibraries() {
        this.allLibraries = libraryMapper.selectAll();
    }


    @Transactional
    public String createLibrary() {
        libraryMapper.insert(libraryToCreate);
        return "/myBatis/libraries?faces-redirect=true";
    }
}
