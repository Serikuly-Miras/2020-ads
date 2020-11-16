package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private Node root;
    private int size = 0;
    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, Key key) {
        if (node == null) return null;
        while (node != null) {
            int compareResult = key.compareTo(node.key);
            if (compareResult < 0) {
                node = node.left;
            } else if (compareResult > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            ++size;
            return new Node(key, value, RED);
        }

        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = put(node.left, key, value);
        } else if (compareResult > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return fixUp(node);
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        rightNode.color = node.color;
        node.color = RED;
        return rightNode;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.color = node.color;
        node.color = RED;
        return leftNode;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        if (isRed(node.right) && isRed(node.left)) {
            flipColors(node);
        }
        return node;
    }

    private Node flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        return node;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value temp = get(key);
        if (temp == null) return null;
        root = delete(root, key);
        --size;
        return temp;
    }

    public void deleteMin() {
        root = deleteMin(root);
        if (root != null) root.color = BLACK;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return null;
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    public void deleteMax() {
        root = deleteMax(root);
        if (root != null) root.color = BLACK;
    }

    private Node deleteMax(Node node) {
        if (isRed(node.left)) {
            node = rotateRight(node);
        }
        if (node.right == null) return null;

        if (!isRed(node.right) && !isRed(node.right.left)) {
            node = moveRedRight(node);
        }
        node.right = deleteMax(node.right);
        return fixUp(node);
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed((node.left.left))) {
                    node = moveRedLeft(node);
                }
                node.left = delete(node.left, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = delete(node.right, key);
            } else if (compareResult == 0 && node.right == null) {
                return null;
            } else {
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                if (node.key == key) {
                    Node min = min(node.right);
                    node.key = min.key;
                    node.value = min.value;
                    node.right = deleteMin(node.right);
                } else {
                    node.right = delete(node.right, key);
                }
            }
        }
        return fixUp(node);
    }

    @Nullable
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

    @Nullable
    @Override
    public Value minValue() {
        Node min = min(root);
        return min == null ? null : min.value;
    }

    @Nullable
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

    @Nullable
    @Override
    public Value maxValue() {
        Node max = max(root);
        return max == null ? null : max.value;
    }

    @Nullable
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

    @Nullable
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

        Node leftNode = ceil(node.left, key);
        return leftNode == null ? node : leftNode;
    }

    @Override
    public int size() {
        return size;
    }
}
