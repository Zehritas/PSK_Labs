package lt.biblioteka.biblioteka.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import lt.biblioteka.biblioteka.entities.Reader;

@ApplicationScoped
@Specializes
public class ReaderFormatterDev extends ReaderFormatterDefault {

    @Override
    public String format(Reader reader) {
        return reader.getName() + " (ID: " + reader.getId() + ")";
    }
}
