package LibraryManagementSystem.models;

import java.util.List;
import java.util.stream.Collectors;

public class Book {
    private Integer bookId;
    private String title;
    private  List<Author> listOfAuthors;
    private  List<Publisher> listOfPublishers;

    public Integer getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getListOfAuthors() {
        return listOfAuthors;
    }

    public List<Publisher> getListOfPublishers() {
        return listOfPublishers;
    }

    public Book(Integer bookId, String title, List<Author> listOfAuthors, List<Publisher> listOfPublishers) {
        this.bookId = bookId;
        this.title = title;
        this.listOfAuthors = listOfAuthors;
        this.listOfPublishers = listOfPublishers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!bookId.equals(book.bookId)) return false;
        if (!title.equals(book.title)) return false;
        if (!listOfAuthors.equals(book.listOfAuthors)) return false;
        return listOfPublishers.equals(book.listOfPublishers);
    }

    @Override
    public int hashCode() {
        int result = bookId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + listOfAuthors.hashCode();
        result = 31 * result + listOfPublishers.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  bookId+" "+title+" "+ listOfAuthors.stream().map(s->String.valueOf(s)).collect(Collectors.joining(",")) +" "+ listOfPublishers.stream().map(s->String.valueOf(s)).collect(Collectors.joining(",")) ;
    }
}
