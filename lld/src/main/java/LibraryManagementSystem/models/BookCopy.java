package LibraryManagementSystem.models;

import java.util.List;

public class BookCopy{
    private Integer bookId;
    private String uniqueId;
    private String borrowerUserId;
    private String dueDate;
    private String rackNumber;

    public BookCopy(Integer bookId, String uniqueId) {
        this.bookId = bookId;
        this.uniqueId = uniqueId;
    }

    public BookCopy() {

    }

    public String getBorrowerUserId() {
        return borrowerUserId;
    }

    public void setBorrowerUserId(String borrowerUserId) {
        this.borrowerUserId = borrowerUserId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(String rackNumber) {
        this.rackNumber = rackNumber;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
