package ru.mail.polis.ads.part1.Serikuly_Miras;

import java.io.*;
import java.util.EmptyStackException;
import java.util.StringTokenizer;

public class Task6124 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Stack<Integer> stack = new Stack<>();
        String input;
        String result;
        do {
            input = in.reader.readLine();
            try {
                switch (input) {
                    case "pop": {
                        int value = stack.pop();
                        result = String.valueOf(value);
                        break;
                    }
                    case "back": {
                        int value = stack.back();
                        result = String.valueOf(value);
                        break;
                    }
                    case "clear": {
                        stack.clear();
                        result = "ok";
                        break;
                    }
                    case "size": {
                        result = Integer.toString(stack.getSize());
                        break;
                    }
                    case "exit": {
                        result = "bye";
                        break;
                    }
                    default: {
                        int arg = Integer.parseInt(input.split(" ")[1]);
                        stack.push(arg);
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

class Stack<E> {
    private int size;
    private Node<E> tail;

    public Stack() {
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