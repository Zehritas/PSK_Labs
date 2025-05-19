package lt.biblioteka.biblioteka.services;

import lt.biblioteka.biblioteka.persistence.LibrariesDAO;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.concurrent.Future;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.CompletableFuture;
import lt.biblioteka.biblioteka.interceptors.LogExecutionTime;


@Stateless
public class LibraryService {

    @Inject
    private LibrariesDAO librariesDAO;

    // Asynchronous method
    // EXAMPLE OUTCOME:
    // 20:06:30,030 INFO  [stdout] (EJB default - 1) [ExecutionTimeInterceptor] Method getBookCountAsync took 5018ms
    @LogExecutionTime // This will now be intercepted
    @Asynchronous
    public Future<Integer> getBookCountAsync(Long id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.completedFuture(0);
        }

        int count = librariesDAO.findOne(id).getBooks().size();
        return new AsyncResult<>(count);
    }
}
