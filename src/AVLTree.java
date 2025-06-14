public class AVLTree<T extends UserNode> {
    private T root;

    private int height(T node) {
        return (node != null) ? node.getHeight() : 0;
    }

    private int getBalance(T node) {
        return (node != null) ? height((T) node.getLeft()) - height((T) node.getRight()) : 0;
    }

    private T rightRotate(T y) {
        T x = (T) y.getLeft();
        T T2 = (T) x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(1 + Math.max(height((T) y.getLeft()), height((T) y.getRight())));
        x.setHeight(1 + Math.max(height((T) x.getLeft()), height((T) x.getRight())));

        return x;
    }

    private T leftRotate(T x) {
        T y = (T) x.getRight();
        T T2 = (T) y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(1 + Math.max(height((T) x.getLeft()), height((T) x.getRight())));
        y.setHeight(1 + Math.max(height((T) y.getLeft()), height((T) y.getRight())));

        return y;
    }

    private T insert(T node, T newNode) {
        if (node == null) return newNode;

        if (newNode.getId() < node.getId())
            node.setLeft(insert((T) node.getLeft(), newNode));
        else if (newNode.getId() > node.getId())
            node.setRight(insert((T) node.getRight(), newNode));
        else {
            System.out.println("Duplicate ID not allowed.");
            return node;
        }

        node.setHeight(1 + Math.max(height((T) node.getLeft()), height((T) node.getRight())));

        int balance = getBalance(node);

        if (balance > 1 && newNode.getId() < ((T) node.getLeft()).getId())
            return rightRotate(node);
        if (balance < -1 && newNode.getId() > ((T) node.getRight()).getId())
            return leftRotate(node);
        if (balance > 1 && newNode.getId() > ((T) node.getLeft()).getId()) {
            node.setLeft(leftRotate((T) node.getLeft()));
            return rightRotate(node);
        }
        if (balance < -1 && newNode.getId() < ((T) node.getRight()).getId()) {
            node.setRight(rightRotate((T) node.getRight()));
            return leftRotate(node);
        }

        System.out.println("Inserted Node: " + newNode.getId() + ", Balance: " + balance);
        return node;
    }

    public void insertNode(T newNode) {
        root = insert(root, newNode);
    }

    public void displayInOrder() {
        inorder(root);
    }

    private void inorder(T node) {
        if (node != null) {
            inorder((T) node.getLeft());
            System.out.println("ID: " + node.getId() + ", Username: " + node.getUsername() + ", Password: " + node.getPassword());
            inorder((T) node.getRight());
        }
    }

    public T search(int id) {
        T current = root;
        while (current != null) {
            if (id == current.getId()) return current;
            else if (id < current.getId()) current = (T) current.getLeft();
            else current = (T) current.getRight();
        }
        return null;
    }
}
