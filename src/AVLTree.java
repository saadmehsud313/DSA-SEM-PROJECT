public class AVLTree<T extends AVLNodeData<T>> {
    private T root;

    private int height(T node) {
        return node == null ? 0 : node.getHeight();
    }//End of height method

    private int getBalance(T node) {
        return node == null ? 0 : height(node.getLeft()) - height(node.getRight());
    }//End of getBalance method

    private T rightRotate(T y) {
        T x = y.getLeft();
        T T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));

        return x;
    }//End of rightRotate method

    private T leftRotate(T x) {
        T y = x.getRight();
        T T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));

        return y;
    }//End of leftRotate method

    private T insert(T node, T newNode) {
        if (node == null)
            return newNode;

        if (newNode.getId() < node.getId())
            node.setLeft(insert(node.getLeft(), newNode));
        else if (newNode.getId() > node.getId())
            node.setRight(insert(node.getRight(), newNode));
        else
            return node; // Duplicate ID

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        int balance = getBalance(node);

        if (balance > 1 && newNode.getId() < node.getLeft().getId())
            return rightRotate(node);
        if (balance < -1 && newNode.getId() > node.getRight().getId())
            return leftRotate(node);
        if (balance > 1 && newNode.getId() > node.getLeft().getId()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (balance < -1 && newNode.getId() < node.getRight().getId()) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }//End of insert method

    public void insertNode(T newNode) {
        root = insert(root, newNode);
    }//End of insertNode method

    public void inorderDisplay() {
        inorder(root);
    }//End of inorderDisplay method

    private void inorder(T node) {
        
        if (node != null) {
            inorder(node.getLeft());
                if(node instanceof UserNode) {
                    UserNode user = (UserNode) node;
                    System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Password: " + user.getPassword());
                } else if (node instanceof AccountNode) {
                    AccountNode account = (AccountNode) node;
                    System.out.println("ID: " + account.getId() + ", Username: " + account.getUsername() + ", Password: " + account.getPassword() +
                            ", PIN: "+account.getPin()+", Type: " + account.getType() + ", Balance: " + account.getBalance() + ", Status: " + account.getStatus());
                }//End of else if 
            inorder(node.getRight());
        }
    }//End of inorder method

    public T search(int id) {
        T current = root;
        while (current != null) {
            if (id == current.getId()) return current;
            else if (id < current.getId()) current = current.getLeft();
            else current = current.getRight();
        }
        return null;
    }// Method to load user data from a file
    public T getRoot() {
        return root;
    }//End of getRoot method
}