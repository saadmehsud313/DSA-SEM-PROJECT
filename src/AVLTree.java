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

    public void deleteNode(int id) {
    root = delete(root, id);
}

private T delete(T node, int id) {
    if (node == null) return null;

    if (id < node.getId()) {
        node.setLeft(delete(node.getLeft(), id));
    } else if (id > node.getId()) {
        node.setRight(delete(node.getRight(), id));
    } else {
        // Node to be deleted found

        // Case 1: One or no child
        if (node.getLeft() == null)
            return node.getRight();
        else if (node.getRight() == null)
            return node.getLeft();

        // Case 2: Two children – get inorder successor
        T minNode = getMinValueNode(node.getRight());

        // Manually copying data from minNode to node
        // You can add more fields if needed depending on type T
        if (node instanceof AccountNode && minNode instanceof AccountNode) {
            AccountNode n = (AccountNode) node;
            AccountNode m = (AccountNode) minNode;
            n.setBalance(m.getBalance());
            n.setPin(m.getPin());
            n.setStatus(m.getStatus());
            n.setType(m.getType());
        }

        if (node instanceof UserNode && minNode instanceof UserNode) {
            UserNode n = (UserNode) node;
            UserNode m = (UserNode) minNode;
            // No extra fields to copy
        }

        // Common fields
        node.setHeight(minNode.getHeight()); // optional
        try {
            java.lang.reflect.Method setUsername = node.getClass().getMethod("setUsername", String.class);
            java.lang.reflect.Method setPassword = node.getClass().getMethod("setPassword", String.class);
            java.lang.reflect.Method setId = node.getClass().getMethod("setId", int.class);

            setUsername.invoke(node, minNode.getUsername());
            setPassword.invoke(node, minNode.getPassword());
            setId.invoke(node, minNode.getId());
        } catch (Exception e) {
            // If your classes don't have setters, ignore this
            System.out.println("Setters missing: " + e.getMessage());
        }

        // Delete the inorder successor node
        node.setRight(delete(node.getRight(), minNode.getId()));
    }

    // Update height
    node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

    // Rebalance
    int balance = getBalance(node);

    // Left Left
    if (balance > 1 && getBalance(node.getLeft()) >= 0)
        return rightRotate(node);

    // Left Right
    if (balance > 1 && getBalance(node.getLeft()) < 0) {
        node.setLeft(leftRotate(node.getLeft()));
        return rightRotate(node);
    }

    // Right Right
    if (balance < -1 && getBalance(node.getRight()) <= 0)
        return leftRotate(node);

    // Right Left
    if (balance < -1 && getBalance(node.getRight()) > 0) {
        node.setRight(rightRotate(node.getRight()));
        return leftRotate(node);
    }

    return node;
}

private T getMinValueNode(T node) {
    T current = node;
    while (current.getLeft() != null) {
        current = current.getLeft();
    }
    return current;
}

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