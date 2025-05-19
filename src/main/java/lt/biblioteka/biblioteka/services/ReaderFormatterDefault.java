package lt.biblioteka.biblioteka.services;

import javax.enterprise.context.ApplicationScoped;
import lt.biblioteka.biblioteka.entities.Reader;
import lt.biblioteka.biblioteka.interfaces.ReaderFormatter;

@ApplicationScoped
public class ReaderFormatterDefault implements ReaderFormatter {

    @Override
    public String format(Reader reader) {
        return reader.getName();
    }
}
