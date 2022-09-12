package Splitwise.models;

public class BorrowData {
    private String userId;
    private Double amount;

    public BorrowData(String userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }


    public String getUserId() {
        return userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
