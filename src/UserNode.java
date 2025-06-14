public class UserNode {
    private int id;
    private String username;
    private String password;
    private int height;
    private UserNode left, right;

    public UserNode() {
        this(0, "", "");
    }

    public UserNode(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.height = 1;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public UserNode getLeft() { return left; }
    public UserNode getRight() { return right; }
    public void setLeft(UserNode left) { this.left = left; }
    public void setRight(UserNode right) { this.right = right; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
