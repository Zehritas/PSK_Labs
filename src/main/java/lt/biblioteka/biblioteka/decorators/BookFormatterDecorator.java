package lt.biblioteka.biblioteka.decorators;

import lt.biblioteka.biblioteka.entities.Book;
import lt.biblioteka.biblioteka.interfaces.BookFormatter;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public abstract class BookFormatterDecorator implements BookFormatter {

    @Inject
    @Delegate
    private BookFormatter delegate;

    @Override
    public String format(Book book) {
        String base = delegate.format(book);
        return (book.getBorrowed() ? "[Borrowed] " : "[Available] ") + base;
    }
}
