package LibraryManagementSystem.repository;


import LibraryManagementSystem.models.BookCopy;
import LibraryManagementSystem.service.LibraryManagementStrategy;

import java.util.List;

public interface RackCrudInterface {
    public Integer count();
    public  Boolean delete(BookCopy entity);
    public  Boolean deleteAll();
    public  Boolean  deleteById(String id);
    public  Boolean existsById(String id);
    public <T> List<T> findAll();
    public  <T>T findById(String id);
    public  void createRacks(Integer valueOf);

    public  String save(List<BookCopy> listOfBookCopy);
}
