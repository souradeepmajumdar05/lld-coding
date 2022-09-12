package LibraryManagementSystem.repository.impl;


import LibraryManagementSystem.models.*;

import LibraryManagementSystem.repository.BookCrudInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookRepository implements BookCrudInterface {
    /***************************Data store********************************/
    // bookid:book
    private static ConcurrentHashMap<Integer,Book> indexBookByid;
    //bookid:<List of bookcopy>
    private static ConcurrentHashMap<String,List<BookCopy>> bookCopyRepo;
    //bookTitle:bookId
    //bookAuthor:bookId
    //bookPublisher:bookId
    //book_copy:bookId
    private static ConcurrentHashMap<IndexObject,List<Integer>> indexMap;
    /*********************************************************************/
    /***************************Instance creation*************************/
    private BookRepository(){
        bookCopyRepo=new ConcurrentHashMap<String,List<BookCopy>>();
        indexBookByid=new ConcurrentHashMap<Integer,Book>();
        indexMap=new ConcurrentHashMap<IndexObject,List<Integer>>();
    }
    private static BookCrudInterface INSTANCE = null;
    public static BookCrudInterface getInstance() {
        if (BookRepository.INSTANCE == null) {
            BookRepository.INSTANCE = new BookRepository();
        }
        return BookRepository.INSTANCE;
    }
    /*********************************************************************/

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public Boolean delete(Book entity) {
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
    public <T>T findById(String id) {
        return null;
    }

    @Override
    public Boolean save(Book book, List<BookCopy> listOfBookCopy) {
        bookCopyRepo.put(book.getBookId().toString(),listOfBookCopy);
        indexForSearching(book,listOfBookCopy);
        return null;
    }
    @Override
    public Boolean updateBookCopy(Integer bookId,List<BookCopy> listOfBookCoopy)
    {
        bookCopyRepo.put(String.valueOf(indexBookByid.get(bookId)),listOfBookCoopy);
        return true;
    }

    @Override
    public List<BookCopy> getListOfBookCopy(Integer bookId)
    {
        List<BookCopy> listOfBookCoopy = bookCopyRepo.get(String.valueOf(bookId));
        return  listOfBookCoopy;
    }

    public Book getBookByBookId(Integer bookId)
    {
        return indexBookByid.get(bookId);
    }
    @Override
    public List<String> getByBookId(Integer bookId) {
        Book book = indexBookByid.get(bookId);
        if(book==null) {
            return Arrays.asList("Invalid Book Copy ID");
        }
        List<BookCopy> listOfBookCoopy = bookCopyRepo.get(String.valueOf(book.getBookId()));
        return createOutput(book, listOfBookCoopy);
    }

    @Override
    public List<Integer> getByAuthorName(String input) {
        IndexObject indexObject=new IndexObject("author_id", input);
        return indexMap.get(indexObject);
    }

    @Override
    public List<Integer> getBookIdByBookCopyId(String input) {
        IndexObject indexObject=new IndexObject("book_copy", input);
        return indexMap.get(indexObject);
    }

    //Book Copy: <book_copy_id> <book_id> <title> <comma_separated_authors> <comma_separated_publishers> <rack_no> <borrowed_by_id> <due_date>
    private List<String> createOutput(Book book, List<BookCopy> listOfBookCoopy) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < listOfBookCoopy.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Book Copy: ")
                    .append(listOfBookCoopy.get(i).getUniqueId())
                    .append(" ").append(book.getBookId())
                    .append(" ").append(book.getTitle())
                    .append(" ").append(book.getListOfAuthors().stream().map(s -> String.valueOf(s.getName())).collect(Collectors.joining(",")))
                    .append(" ").append(book.getListOfPublishers().stream().map(s -> String.valueOf(s.getName())).collect(Collectors.joining(",")))
                    .append(" ").append(listOfBookCoopy.get(i).getRackNumber() != null ? listOfBookCoopy.get(i).getRackNumber() : "")
                    .append(" ").append(listOfBookCoopy.get(i).getDueDate() != null ? listOfBookCoopy.get(i).getDueDate() : "");
            output.add(stringBuilder.toString());
        }
        return output;
    }

    private void indexForSearching(Book book, List<BookCopy> listOfBookCopy) {
        indexBookByid.put(book.getBookId(), book);
        for (Author author : book.getListOfAuthors()) {
            addIndex(new IndexObject("author_id", author.getName()), book);
        }
        addIndex(new IndexObject("title", book.getTitle()), book);
        for (Publisher publisher : book.getListOfPublishers()) {
            addIndex(new IndexObject("publisher", publisher.getName()), book);
        }
        for(BookCopy bookCopy:listOfBookCopy) {
            addIndex(new IndexObject("book_copy", bookCopy.getUniqueId()), book);
        }
    }

    private void addIndex(IndexObject indexObject, Book book) {
        //System.out.println(" value :"+indexObject.toString()+" : "+book.toString());
        if (indexMap.get(indexObject) != null) {
            indexMap.get(indexObject).add(book.getBookId());
        } else {
            indexMap.put(indexObject, new ArrayList<>(Arrays.asList(book.getBookId())));
        }
    }
}
