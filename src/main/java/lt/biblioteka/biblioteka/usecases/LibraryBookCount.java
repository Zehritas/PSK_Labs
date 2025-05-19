package lt.biblioteka.biblioteka.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.biblioteka.biblioteka.services.LibraryService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Named
@SessionScoped // Needs to retain state while async task runs
public class LibraryBookCount implements Serializable {

    @Inject
    private LibraryService libraryService;

    @Getter @Setter
    private Long libraryId;

    @Getter
    private Integer bookCount;

    @Getter
    private boolean loading = false;

    private Future<Integer> futureResult;

    public void fetchBookCount() {
        if (libraryId != null) {
            this.loading = true;
            futureResult = libraryService.getBookCountAsync(libraryId);
        }
    }

    public void checkIfDone() {
        if (futureResult != null && futureResult.isDone()) {
            try {
                bookCount = futureResult.get();
            } catch (Exception e) {
                bookCount = -1;
            } finally {
                loading = false;
            }
        }
    }

}
