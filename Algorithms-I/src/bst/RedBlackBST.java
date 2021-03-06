package bst;

public class RedBlackBST {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        boolean color; // color of this node
        public Node(Key k, Value v, boolean c) {
            key = k;
            value = v;
            color = c;
        }
    }

    public void put(Key k, Value v, boolean color) {
        root = put(root, k, v, color);
    }

    private Node put(Node h, Key key, Value value, boolean color) {
        // Insert the node
        if (h == null)
            return new Node(key, value, color);
        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, value, color);
        else if (cmp > 0)
            h.right = put(h.right, key, value, color);
        else
            h.value = value;

        // Rotate if satisfies below condition
        if (!isRed(h.left) && isRed(h.right))
            h = rotateLeft(h);
        else if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        else if (isRed(h.left) && isRed(h.right))
            flipColors(h);
        return h;
    }

    // Checks for redness of a node
    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    // Perform left rotation
    private Node rotateLeft(Node currentNode) {
        assert (isRed(currentNode.right));

        Node newNode = currentNode.right;
        currentNode.right = newNode.left;
        newNode.left = currentNode;
        newNode.color = currentNode.color;
        currentNode.color = RED;
        return newNode;
    }

    // Perform right rotation
    private Node rotateRight(Node currentNode) {
        assert (isRed(currentNode.left));

        Node newNode = currentNode.left;
        currentNode.left = newNode.right;
        newNode.right = currentNode;
        newNode.color = currentNode.color;
        currentNode.color = RED;
        return newNode;
    }

    private void flipColors(Node h) {
        assert (!isRed(h));
        assert (isRed(h.left));
        assert (isRed(h.right));

        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
}