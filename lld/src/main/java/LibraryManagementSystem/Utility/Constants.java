package LibraryManagementSystem.Utility;

public class Constants {
    public static String FILE_PATH ="/home/souradeep/Documents/lld/src/main/resources/LibrarryManagementSystem/input1.txt";
    //public static String FILE_PATH ="/home/souradeep/Documents/lld/src/main/resources/KeyValueStore/input2.txt";
    //public static String FILE_PATH ="/home/souradeep/Documents/lld/src/main/resources/KeyValueStore/input3.txt";
    public static String CREATE_LIBRARY="create_library";
    public static String ADD_BOOK="add_book";
    public static String BOOK_ID="book_id";
    public static String AUTHOR_ID="author_id";
    public static String REMOVE_BOOK_COPY="remove_book_copy";
    public static String PRINT_BORROWED="print_borrowed";
    public static String BORROW_BOOK="borrow_book";
    public static String RETURN_BOOK_COPY="return_book_copy";
    public static String SEARCH="search";
    public static String EXIT="exit";
    public static String INPUTERROR="Input line didn't have expected operation command create_library/add_book/book_id/author_id/remove_book_copy/print_borrowed/borrow_book/return_book_copy/exit inputline: ";
    public static String FILE_PARSING_ERROR="error occured parsing the file";
    public static Integer maxAllowedBooks=5;
}
