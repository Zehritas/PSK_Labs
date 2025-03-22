package lt.biblioteka.biblioteka.persistence;

import lt.biblioteka.biblioteka.entities.Library;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class LibrariesDAO {


    @Inject
    private EntityManager em;

    public List<Library> loadAll() {
        return em.createNamedQuery("Library.findAll", Library.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Library library){
        this.em.persist(library);
    }

    public Library findOne(Long id) {
        return em.find(Library.class, id);
    }

    public List<Library> findTeamsByNameFragment(String nameFragment) {  // Paieska pagal pavadinimo fragmenta
        TypedQuery<Library> query = em.createQuery(
                "SELECT t FROM Library t WHERE LOWER(t.name) LIKE LOWER(:nameFragment)",  // Naudojame variable binding bandant isvengti sql injekcijos
                Library.class
        );
        query.setParameter("nameFragment", "%" + nameFragment + "%");
        return query.getResultList();
    }
}