public class AccountNode extends UserNode {
    private int id, pin, height;
    private String username, password, type, status;
    private float balance;
    private AccountNode left, right;

    public AccountNode() {
        this(0, "", "", "active", "savings", 0.0f, 0);
    }

    public AccountNode(int id, String username, String password, String status, String type, float balance, int pin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.type = type;
        this.balance = balance;
        this.pin = pin;
        this.height = 1;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getPin() { return pin; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public float getBalance() { return balance; }

    public void setBalance(float balance) {
        if (balance >= 0) this.balance = balance;
        else System.out.println("Balance cannot be negative.");
    }

    public AccountNode getLeft() { return left; }
    public AccountNode getRight() { return right; }
    public void setLeft(AccountNode left) { this.left = left; }
    public void setRight(AccountNode right) { this.right = right; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
