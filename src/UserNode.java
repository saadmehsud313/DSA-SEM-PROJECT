public class UserNode implements AVLNodeData<UserNode> {
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

    @Override
    public int getId() { return id; }
    @Override
    public String getUsername() { return username; }
    @Override
    public String getPassword() { return password; }
    @Override
    public UserNode getLeft() { return left; }
    @Override
    public UserNode getRight() { return right; }
    @Override
    public void setLeft(UserNode left) { this.left = left; }
    @Override
    public void setRight(UserNode right) { this.right = right; }
    @Override
    public int getHeight() { return height; }
    @Override
    public void setHeight(int height) { this.height = height; }
}
