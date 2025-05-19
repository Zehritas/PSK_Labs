package lt.biblioteka.biblioteka.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.biblioteka.biblioteka.mybatis.dao.BookMapper;
import lt.biblioteka.biblioteka.mybatis.dao.LibraryMapper;
import lt.biblioteka.biblioteka.mybatis.dao.ReaderMapper;
import lt.biblioteka.biblioteka.mybatis.model.Book;
import lt.biblioteka.biblioteka.mybatis.model.Library;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
@RequestScoped
public class LibrariesForPageMyBatis {
    @Inject
    private LibraryMapper libraryMapper;

    @Inject
    private BookMapper bookMapper;

    @Inject
    private ReaderMapper readerMapper;

    @Getter
    @Setter
    private Book bookToCreate = new Book();

    @Getter
    private Library library;

    @Getter
    private List<Book> libraryBooks;


    @PostConstruct
    public void init() {
        this.loadAll();
    }

    private void loadAll()
    {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long libraryId = Long.parseLong(requestParameters.get("libraryId"));
        this.library = libraryMapper.selectByPrimaryKey(libraryId);
    }


    @Transactional
    public void createBook(){
        bookToCreate.setLibraryId(this.library.getId());
        bookMapper.insert(bookToCreate);
    }
}
