package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, Node left, Node right, int height) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = height;
        }

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, Key key) {
        if (node == null) return null;

        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return get(node.left, key);
        } else if (compareResult > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, 1);
        }

        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = put(node.left, key, value);
        } else if (compareResult > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        fixHeight(node);
        node = balance(node);
        return node;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        fixHeight(node);
        fixHeight(leftNode);
        return leftNode;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        fixHeight(node);
        fixHeight(rightNode);
        return rightNode;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node node = get(root, key);
        if (node == null) return null;
        root = remove(root, key);
        --size;
        return node.value;
    }

    private Node remove(Node node, Key key) {
        if (node == null) return null;

        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = remove(node.left, key);
        } else if (compareResult > 0) {
            node.right = remove(node.right, key);
        } else {
            node = innerDelete(node);
        }

        return node;
    }

    private Node innerDelete(Node node) {
        if (node.right == null) return node.left;
        if (node.left == null) return node.right;

        Node t = node;
        node = min(t.right);
        node.right = deleteMin(t.right);
        node.left = t.left;

        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    @Override
    public Key min() {
        Node min = min(root);
        return min == null ? null : min.key;
    }

    private Node min(Node node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public Value minValue() {
        Node min = min(root);
        return min == null ? null : min.value;
    }

    @Override
    public Key max() {
        Node max = max(root);
        return max == null ? null : max.key;
    }

    private Node max(Node node) {
        if (node == null) return null;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public Value maxValue() {
        Node max = max(root);
        return max == null ? null : max.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        if (size == 0) return null;
        Node node = floor(root, key);
        return node == null ? null : node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;

        if (key == node.key) {
            return node;
        }

        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }

        Node rightNode = floor(node.right, key);
        return rightNode == null ? node : rightNode;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        if (size == 0) return null;
        Node node = ceil(root, key);
        return node == null ? null : node.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) return null;
        if (key == node.key) return node;

        if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }

        Node leftNode = floor(node.left, key);
        return leftNode == null ? node : leftNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
}
