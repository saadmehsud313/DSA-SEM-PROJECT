public interface AVLNodeData<T> {
    int getId();
    String getUsername();
    String getPassword();
    int getHeight();
    void setHeight(int h);
    T getLeft();
    void setLeft(T left);
    T getRight();
    void setRight(T right);
}
