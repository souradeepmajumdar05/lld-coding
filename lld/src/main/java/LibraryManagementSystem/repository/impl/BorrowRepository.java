package LibraryManagementSystem.repository.impl;

import LibraryManagementSystem.models.BookCopy;
import LibraryManagementSystem.repository.BorrowInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BorrowRepository implements BorrowInterface {
    //userid : List<BookCopy>
    private static ConcurrentHashMap<String, List<BookCopy>> borrow = null;
    public static BorrowInterface INSTANCE = null;

    public static BorrowRepository getInstance() {
        if (BorrowRepository.INSTANCE == null) {
            BorrowRepository.INSTANCE = new BorrowRepository();
        }
        return (BorrowRepository) BorrowRepository.INSTANCE;
    }

    private BorrowRepository() {
        borrow = new ConcurrentHashMap<String, List<BookCopy>>();
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public Boolean delete(BookCopy entity) {
        return null;
    }

    @Override
    public Boolean deleteAll() {
        return null;
    }

    @Override
    public Boolean deleteById(String id) {
        return null;
    }

    @Override
    public Boolean existsById(String id) {
        return null;
    }

    @Override
    public <T> List<T> findAll() {
        return null;
    }

    @Override
    public List<BookCopy> findById(String id) {
        return borrow.get(id);
    }

    @Override
    public Boolean save(String userId, BookCopy bookCopy) {
        if (borrow.get(userId) != null) {
            borrow.get(userId).add(bookCopy);
        } else {
            borrow.put(userId, new ArrayList<>(Arrays.asList(bookCopy)));
        }
        return null;
    }
}
