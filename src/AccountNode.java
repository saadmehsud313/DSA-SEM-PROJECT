public class AccountNode implements AVLNodeData<AccountNode> {
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

    @Override
    public int getId() { return id; }
    @Override
    public String getUsername() { return username; }
    @Override
    public String getPassword() { return password; }
    @Override
    public AccountNode getLeft() { return left; }
    @Override
    public AccountNode getRight() { return right; }
    @Override
    public void setLeft(AccountNode left) { this.left = left; }
    @Override
    public void setRight(AccountNode right) { this.right = right; }
    @Override
    public int getHeight() { return height; }
    @Override
    public void setHeight(int height) { this.height = height; }
    public int getPin() { return pin; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public float getBalance() { return balance; }

    public void setBalance(float balance) {
        if (balance >= 0) this.balance = balance;
        else System.out.println("Balance cannot be negative.");
    }
    public void setPin(int pin) {
        if (pin >= 0) this.pin = pin;
        else System.out.println("PIN cannot be negative.");
        }

    public void setType(String type) {
                this.type = type;
    }    
    public void setStatus(String status){
        this.status = status;
    }

}
