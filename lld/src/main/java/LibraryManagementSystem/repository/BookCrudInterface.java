package LibraryManagementSystem.repository;

import LibraryManagementSystem.models.Book;
import LibraryManagementSystem.models.BookCopy;

import java.util.List;

public interface BookCrudInterface {

    public Integer count();

    public Boolean delete(Book entity);

    public Boolean deleteAll();

    public Boolean deleteById(String id);

    public Boolean existsById(String id);

    public <T> List<T> findAll();

    public  <T> T findById(String id);

    public Boolean save(Book entity, List<BookCopy> listOfBookCopy);

    List<String> getByBookId(Integer valueOf);

    List<Integer> getByAuthorName(String input);

    List<Integer> getBookIdByBookCopyId(String s);

    List<BookCopy> getListOfBookCopy(Integer bookId);

    Boolean updateBookCopy(Integer bookId, List<BookCopy> listOfBookCoopy);

    Book getBookByBookId(Integer bookId);
}
