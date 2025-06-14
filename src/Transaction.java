public class Transaction {
    int id;
    String type; // "deposit" or "withdraw"
    float amount;
    String date;
    String source;
    Transaction next;

    public Transaction(int id, String type, float amount, String source) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = java.time.LocalDateTime.now().toString();
        this.next = null;
        this.source=source;
    }
}
