package lt.biblioteka.biblioteka.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.biblioteka.biblioteka.persistence.ReadersDAO;
import lt.biblioteka.biblioteka.entities.Reader;
import lt.biblioteka.biblioteka.services.ReaderFormatterDefault;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Readers {

    @Inject
    private ReadersDAO readersDAO;

    @Getter @Setter
    private Reader readerToCreate = new Reader();


    @Inject
    private ReaderFormatterDefault formatter;
    @Getter
    private List<Reader> allReaders;

    @PostConstruct
    public void init(){
        loadAllReaders();
    }

    @Transactional
    public void createReader(){
        this.readersDAO.persist(readerToCreate);
    }

    private void loadAllReaders(){
        this.allReaders = readersDAO.loadAll();
    }

    public String formatReader(Reader reader) {
        return formatter.format(reader);
    }
}
