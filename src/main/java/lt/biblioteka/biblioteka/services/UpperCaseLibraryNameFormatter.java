package lt.biblioteka.biblioteka.services;

import lt.biblioteka.biblioteka.interfaces.LibraryNameFormatter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class UpperCaseLibraryNameFormatter implements LibraryNameFormatter {
    @Override
    public String format(String name) {
        return name.toUpperCase(); // Uppercase transformation
    }
}
