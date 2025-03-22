package lt.biblioteka.biblioteka.persistence;

import lt.biblioteka.biblioteka.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class BooksDAO {


    @Inject
    private EntityManager em;

//    public List<Book> loadAll() {
//        return em.createNamedQuery("Library.findAll", Library.class).getResultList();
//    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Book book){
        this.em.persist(book);
    }

    public Book findOne(Integer id) {
        return em.find(Book.class, id);
    }

//    public List<Library> findTeamsByNameFragment(String nameFragment) {  // Paieska pagal pavadinimo fragmenta
//        TypedQuery<Library> query = em.createQuery(
//                "SELECT t FROM Library t WHERE LOWER(t.name) LIKE LOWER(:nameFragment)",  // Naudojame variable binding bandant isvengti sql injekcijos
//                Library.class
//        );
//        query.setParameter("nameFragment", "%" + nameFragment + "%");
//        return query.getResultList();
//    }

}
