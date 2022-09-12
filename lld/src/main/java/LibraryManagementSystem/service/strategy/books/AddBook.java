package LibraryManagementSystem.service.strategy.books;

import LibraryManagementSystem.models.Author;
import LibraryManagementSystem.models.Book;
import LibraryManagementSystem.models.BookCopy;
import LibraryManagementSystem.models.Publisher;
import LibraryManagementSystem.repository.BookCrudInterface;
import LibraryManagementSystem.repository.RackCrudInterface;
import LibraryManagementSystem.repository.impl.BookRepository;
import LibraryManagementSystem.repository.impl.LibraryRackRepository;
import LibraryManagementSystem.service.LibraryManagementStrategy;

import java.util.ArrayList;
import java.util.List;

public class AddBook implements LibraryManagementStrategy {
    BookCrudInterface bookRepo=null;
    RackCrudInterface rackRepo=null;
    private AddBook(){
        bookRepo= BookRepository.getInstance();
        rackRepo= LibraryRackRepository.getInstace();
    }
    private static LibraryManagementStrategy INSTANCE=null;
    public static LibraryManagementStrategy getInstace() {
        if(AddBook.INSTANCE==null)
        {
            AddBook.INSTANCE=new AddBook();
        }
        return AddBook.INSTANCE;
    }


    @Override
    public String doOperation(String input) throws Exception {
        String[] inputArr = input.split(" ");
        Book book = createBookObject(inputArr);
        List<BookCopy> listOfBookCopy= getListOfBookCopy(inputArr);
        String comaSeperatedRacksAddedIn=addToLibraryRackRepository(inputArr[1],listOfBookCopy);
        if(!comaSeperatedRacksAddedIn.equals("Rack not available")) {
            addToBookRepositpory(book, listOfBookCopy);
        }
        return comaSeperatedRacksAddedIn;
    }

    private String addToLibraryRackRepository(String rackStartPosition, List<BookCopy> listOfBookCopy) {
        return rackRepo.save(listOfBookCopy);
    }

    private void addToBookRepositpory(Book book, List<BookCopy> listOfBookCopy) {
        bookRepo.save(book,listOfBookCopy);
    }

    public Book createBookObject(String[] inputArr)
    {
        String[] authors=inputArr[3].split(",");
        List<Author> listOfAuthor = new ArrayList<>();
        for(int i =0;i<authors.length;i++)
        {
            Author author=new Author(authors[i]);
            listOfAuthor.add(author);
        }
        List<Publisher> listOfPublishers = new ArrayList<>();
        String[] publishers=inputArr[4].split(",");
        for(int i =0;i<publishers.length;i++)
        {
            Publisher publisher=new Publisher(publishers[i]);
            listOfPublishers.add(publisher);
        }
        return new Book(Integer.valueOf(inputArr[1]),inputArr[2],listOfAuthor,listOfPublishers);
    }
    public List<BookCopy> getListOfBookCopy(String[] inputArr)
    {
        String[] bookCopys=inputArr[5].split(",");
        List<BookCopy> listOfBookCopy=new ArrayList<>();
        for(int i=0 ;i<bookCopys.length;i++){
            BookCopy bookCopy = new BookCopy(Integer.valueOf(inputArr[1]),bookCopys[i]);
            listOfBookCopy.add(bookCopy);
        }
        return listOfBookCopy;
    }
}
