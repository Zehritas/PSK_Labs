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
public class ReaderForPage {

    @Inject
    private ReadersDAO readersDAO;

    private Reader reader;

    // This method gets called during page load to initialize the reader
    @PostConstruct
    public void init() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long readerId = Long.parseLong(requestParameters.get("readerId"));
        this.reader = readersDAO.findOne(readerId);  // Fetch the library based on ID
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

}