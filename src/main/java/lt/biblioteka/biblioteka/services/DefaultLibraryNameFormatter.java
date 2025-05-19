package lt.biblioteka.biblioteka.services;

import lt.biblioteka.biblioteka.interfaces.LibraryNameFormatter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefaultLibraryNameFormatter implements LibraryNameFormatter {
    @Override
    public String format(String name) {
        return name;
    }
}
