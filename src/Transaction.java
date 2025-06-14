public class Transaction {
    int id;
    String type; // "deposit" or "withdraw"
    float amount;
    String date;
    Transaction next;

    public Transaction(int id, String type, float amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = java.time.LocalDateTime.now().toString();
        this.next = null;
    }
}
