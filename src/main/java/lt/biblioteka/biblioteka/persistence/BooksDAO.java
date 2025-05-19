package lt.biblioteka.biblioteka.persistence;

import lt.biblioteka.biblioteka.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.TypedQuery;
import javax.transaction.RollbackException;
import javax.transaction.Transactional;
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


    @Transactional
    public void toggleBorrowed(Long id) throws RollbackException, InterruptedException {
        try {
            Book book = em.find(Book.class, id);
            Thread.sleep(5000); // Simulate delay
            book.setBorrowed(!book.getBorrowed());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // reset interrupt flag
        }
    }

//    @Transactional
//    public void toggleBorrowed(Book detachedBook) {
//        try {
//
//            Book managedBook = em.merge(detachedBook); // Triggers version check
//            Thread.sleep(5000);
//            managedBook.setBorrowed(!managedBook.getBorrowed());
//            // If successful, the transaction will commit automatically
//        } catch (OptimisticLockException e) {
//            // Handle the version conflict (log or show user-friendly message)
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "The book has been updated by another user. Please try again.", null));
//
//            // Optionally, rethrow the exception to let the transaction be rolled back automatically
//            throw e; // This will cause a rollback (but ensure you notify the user about this)
//        }
//        catch (InterruptedException e)
//        {
//
//        }
//    }

public List<Book> findBooksByLibraryId(Long libraryId) {
    return em.createQuery("SELECT b FROM Book b WHERE b.library.id = :libraryId", Book.class)
            .setParameter("libraryId", libraryId)
            .getResultList();
}

}
