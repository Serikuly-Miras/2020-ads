package ru.mail.polis.ads.part1.Serikuly_Miras;

import java.io.*;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Task3837 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; ++i) {
            char[] expression = in.next().toCharArray();
            StackForTwo<Node> stack = new StackForTwo<>();
            for (char symbol : expression) {
                if (Character.isLowerCase(symbol)) {
                    stack.push(new Node(symbol, null, null));
                } else {
                    Node left = stack.pop();
                    Node right = stack.pop();
                    stack.push(new Node(symbol, left, right));
                }
            }
            out.println(contInOrder(stack.pop()));
        }
    }

    private static String contInOrder(Node root) {
        QueueTaskTwo<Node> queue = new QueueTaskTwo<>();
        StringBuilder builder = new StringBuilder();
        queue.push(root);
        Node current;
        while (queue.getSize() != 0) {
            current = queue.pop();
            if (current.right != null)
                queue.push(current.right);
            if (current.left != null)
                queue.push(current.left);
            builder.insert(0, current.item);
        }
        return builder.toString();
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    static class Node {
        char item;
        Node left;
        Node right;

        public Node(char item, Node left, Node right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }
}

class StackForTwo<E> {
    private int size;
    private Node<E> tail;

    public StackForTwo() {
        this.size = 0;
        this.tail = null;
    }


    private static class Node<E> {
        E item;
        Node<E> prev;

        Node(E element, Node<E> prev) {
            this.item = element;
            this.prev = prev;
        }

        void clear() {
            this.item = null;
            this.prev = null;
        }
    }

    public void push(E element) {
        this.tail = new Node<>(element, this.tail);
        size++;
    }

    public E pop() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();

        Node<E> current = this.tail;
        this.tail = this.tail.prev;
        size--;
        return current.item;
    }

    public E back() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        return this.tail.item;
    }

    public void clear() {
        Node<E> current = this.tail;
        Node<E> next;
        while (current != null) {
            next = current.prev;
            current.clear();
            current = next;
        }
        this.size = 0;
        this.tail = null;
    }

    public int getSize() {
        return size;
    }
}

class QueueTaskTwo<E> {
    private int size;
    private Node<E> head;
    private Node<E> tail;

    public QueueTaskTwo() {
        this.size = 0;
        this.tail = null;
        this.head = null;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        void clear() {
            this.item = null;
            this.next = null;
        }
    }

    public void push(E element) {
        Node<E> node = new Node<E>(element, null);
        if (this.tail != null)
            this.tail.next = node;
        if (this.head == null)
            this.head = node;
        this.tail = node;
        size++;
    }

    public E pop() throws NoSuchElementException {
        if (size == 0)
            throw new EmptyStackException();

        Node<E> current = this.head;
        this.head = this.head.next;
        size--;
        return current.item;
    }

    public E front() throws NoSuchElementException {
        if (size == 0)
            throw new EmptyStackException();
        return this.head.item;
    }

    public void clear() {
        Node<E> current = this.head;
        Node<E> next;
        while (current != null) {
            next = current.next;
            current.clear();
            current = next;
        }

        this.size = 0;
        this.tail = null;
        this.head = null;
    }

    public int getSize() {
        return size;
    }
}
