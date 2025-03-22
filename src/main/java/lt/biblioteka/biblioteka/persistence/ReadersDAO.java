package lt.biblioteka.biblioteka.persistence;

import lt.biblioteka.biblioteka.entities.Book;
import lt.biblioteka.biblioteka.entities.Library;
import lt.biblioteka.biblioteka.entities.Reader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class ReadersDAO {


    @Inject
    private EntityManager em;

//    public List<Book> loadAll() {
//        return em.createNamedQuery("Library.findAll", Library.class).getResultList();
//    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Reader reader){
        this.em.persist(reader);
    }

    public List<Reader> loadAll() {
        return em.createNamedQuery("Reader.findAll", Reader.class).getResultList();
    }

    public Reader findOne(Long id) {
        return em.find(Reader.class, id);
    }
}
