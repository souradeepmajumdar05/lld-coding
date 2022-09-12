package LibraryManagementSystem.service.strategy.books;

import LibraryManagementSystem.Utility.Constants;
import LibraryManagementSystem.models.BookCopy;
import LibraryManagementSystem.repository.BookCrudInterface;
import LibraryManagementSystem.repository.BorrowInterface;
import LibraryManagementSystem.repository.RackCrudInterface;
import LibraryManagementSystem.repository.impl.BookRepository;
import LibraryManagementSystem.repository.impl.BorrowRepository;
import LibraryManagementSystem.repository.impl.LibraryRackRepository;
import LibraryManagementSystem.repository.impl.UserRepository;
import LibraryManagementSystem.service.LibraryManagementStrategy;

import java.util.List;

public class BorrowBook implements LibraryManagementStrategy {
    BorrowInterface borrowRepo = null;
    BookCrudInterface bookRepo = null;
    RackCrudInterface rackRepo = null;
    UserRepository userRepo = null;

    private BorrowBook() {
        borrowRepo = BorrowRepository.getInstance();

        rackRepo = LibraryRackRepository.getInstace();
        bookRepo = BookRepository.getInstance();
        userRepo = UserRepository.getIstance();
    }

    private static LibraryManagementStrategy INSTANCE = null;

    public static LibraryManagementStrategy getInstance() {
        if (BorrowBook.INSTANCE == null) {
            BorrowBook.INSTANCE = new BorrowBook();
        }
        return BorrowBook.INSTANCE;
    }

    //borrow_book 1 user1 2020-12-31
    @Override
    public String doOperation(String input) throws Exception {
        String[] inputArr = input.split(" ");
        //user exist check
        if (userRepo.containsUser(inputArr[2])) {
            //book copy list fetch
            List<BookCopy> listOfBorrowedBooks = borrowRepo.findById(inputArr[2]);
            if (listOfBorrowedBooks == null || listOfBorrowedBooks.size() < Constants.maxAllowedBooks ||
                    listOfBorrowedBooks.size() == 0) {
                List<BookCopy> listOfBookCoopy = bookRepo.getListOfBookCopy(Integer.valueOf(inputArr[1]));
                if(listOfBookCoopy==null){
                    return "Invalid Book ID";
                }
                if (listOfBookCoopy.size() > 0) {
                    BookCopy tempBookCopy = null;
                    BookCopy removeBookCopy = null;
                    Boolean flag=false;
                    for (BookCopy bookCopy : listOfBookCoopy) {
                        //book removed from rack
                        if (rackRepo.deleteById(bookCopy.getRackNumber())) {
                            tempBookCopy=bookCopy;
                            removeBookCopy=bookCopy;
                            tempBookCopy.setBorrowerUserId(inputArr[2]);
                            tempBookCopy.setDueDate(inputArr[3]);
                            borrowRepo.save(inputArr[2],tempBookCopy);
                            flag=true;
                            break;

                        }
                    }
                    if(flag){
                        listOfBookCoopy.remove(removeBookCopy);
                        listOfBookCoopy.add(tempBookCopy);
                        bookRepo.save(bookRepo.getBookByBookId(tempBookCopy.getBookId()),listOfBookCoopy);
                        return "Borrowed book from rack : "+tempBookCopy.getRackNumber();
                    }
                    else{
                        return "Not available";
                    }
                }
                else{
                    return "Not available";
                }
            } else {
                return "Overlimit";
            }
        }


        return null;
    }
}
