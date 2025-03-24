package lt.biblioteka.biblioteka.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@NamedQueries({
        @NamedQuery(name = "Library.findAll", query = "select t from Library as t")
})
@Table(name = "Library")
public class Library {
    @GeneratedValue
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @ManyToMany
    private List<Reader> readers;

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(getId(), library.getId()) && Objects.equals(getName(), library.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
