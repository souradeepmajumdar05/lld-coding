package LibraryManagementSystem.service.strategy.books;

import LibraryManagementSystem.models.BookCopy;
import LibraryManagementSystem.repository.BorrowInterface;
import LibraryManagementSystem.repository.impl.BorrowRepository;
import LibraryManagementSystem.service.LibraryManagementStrategy;
import LibraryManagementSystem.service.strategy.LibraryManagementImpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PrintBorrowed implements LibraryManagementStrategy {
    BorrowInterface borrow = null;

    private PrintBorrowed() {
        borrow = BorrowRepository.getInstance();
    }

    private static LibraryManagementStrategy INSTANCE = null;

    public static LibraryManagementStrategy getInstance() {
        if (PrintBorrowed.INSTANCE == null) {
            PrintBorrowed.INSTANCE = new PrintBorrowed();
        }
        return PrintBorrowed.INSTANCE;
    }

    @Override
    public String doOperation(String input) throws Exception {
        String[] inputArr = input.split(" ");
        List<BookCopy> listOfBookCopy = borrow.findById(inputArr[1]);
        return listOfBookCopy == null ? "User has not borrowed anything" : listOfBookCopy.stream()
                .filter(Objects::nonNull)
                .map(s -> "Book Copy : " + s.getUniqueId() + " " + s.getDueDate())
                .collect(Collectors.joining("\n"));

    }
}
