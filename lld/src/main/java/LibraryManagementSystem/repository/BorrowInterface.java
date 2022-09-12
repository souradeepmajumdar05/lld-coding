package LibraryManagementSystem.repository;

import LibraryManagementSystem.models.BookCopy;

import java.util.List;

public interface BorrowInterface {
    public Integer count();
    public  Boolean delete(BookCopy entity);
    public  Boolean deleteAll();
    public  Boolean  deleteById(String id);
    public  Boolean existsById(String id);
    public <T> List<T> findAll();
    public List<BookCopy> findById(String id);
    public Boolean save(String s, BookCopy entity);
}
