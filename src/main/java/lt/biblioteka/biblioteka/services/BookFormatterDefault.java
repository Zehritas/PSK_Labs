package lt.biblioteka.biblioteka.services;

import lt.biblioteka.biblioteka.entities.Book;
import lt.biblioteka.biblioteka.interfaces.BookFormatter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookFormatterDefault implements BookFormatter {

    @Override
    public String format(Book book) {
        return book.getName();
    }
}
