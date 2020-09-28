package ru.mail.polis.ads.part1.Serikuly_Miras;

import java.io.*;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Task6125 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Queue<Integer> queue = new Queue<>();
        String input;
        String result;
        do {
            input = in.reader.readLine();
            try {
                switch (input) {
                    case "pop": {
                        int value = queue.pop();
                        result = String.valueOf(value);
                        break;
                    }
                    case "front": {
                        int value = queue.front();
                        result = String.valueOf(value);
                        break;
                    }
                    case "clear": {
                        queue.clear();
                        result = "ok";
                        break;
                    }
                    case "size": {
                        result = Integer.toString(queue.getSize());
                        break;
                    }
                    case "exit": {
                        result = "bye";
                        break;
                    }
                    default: {
                        int arg = Integer.parseInt(input.split(" ")[1]);
                        queue.push(arg);
                        result = "ok";
                        break;
                    }
                }
            } catch (Exception e) {
                result = "error";
            }
            out.println(result);
        } while (!input.equals("exit"));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Queue<E> {
    private int size;
    private Node<E> head;
    private Node<E> tail;

    public Queue() {
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
