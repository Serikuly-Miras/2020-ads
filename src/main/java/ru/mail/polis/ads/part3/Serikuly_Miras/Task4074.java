package ru.mail.polis.ads.part3.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7536835
public class Task4074 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        HeapMax leftHeap = new HeapMax(500_000);
        HeapMin rightHeap = new HeapMin(500_000);

        try (BufferedOutputStream output = new BufferedOutputStream(System.out)) {
            while (true) {
                int x = in.nextInt();
                add(x, leftHeap, rightHeap);
                byte[] buffer = (getMedian(leftHeap, rightHeap) + "\n").getBytes();
                output.write(buffer, 0, buffer.length);
            }
        }
    }

    private static void add(int value, HeapMax leftHeap, HeapMin rightHeap) {
        if (leftHeap.getSize() == rightHeap.getSize()) {
            if (leftHeap.getSize() == 0) {
                leftHeap.insert(value);
            } else if (value > rightHeap.peekMin()) {
                int t = rightHeap.pollMin();
                leftHeap.insert(t);
                rightHeap.insert(value);
            } else {
                leftHeap.insert(value);
            }
        } else {
            if (value > leftHeap.peekMax()) {
                rightHeap.insert(value);
            } else {
                int t = leftHeap.pollMax();
                leftHeap.insert(value);
                rightHeap.insert(t);
            }
        }
    }

    public static int getMedian(HeapMax leftHeap, HeapMin rightHeap) {
        if (leftHeap.getSize() == rightHeap.getSize())
            return (leftHeap.peekMax() + rightHeap.peekMin()) / 2;
        return leftHeap.peekMax();
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
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

class HeapMax {
    private final int[] array;
    private int size = 0;

    public HeapMax(int size) {
        array = new int[size + 2];
    }

    public void insert(int value) {
        array[++size] = value;
        swim(size);
    }

    public int pollMax() {
        int maxValue = array[1];
        swap(1, size--);
        sink(1);
        return maxValue;
    }

    public int peekMax() {
        return array[1];
    }

    public int getSize() {
        return size;
    }

    private void swim(int node) {
        while (node > 1 && array[node] > array[node / 2]) {
            swap(node, node / 2);
            node /= 2;
        }
    }

    private void sink(int node) {
        while (2 * node <= size) {
            int child = 2 * node;
            if (child < size && array[child] < array[child + 1])
                ++child;
            if (array[node] >= array[child])
                break;
            swap(node, child);
            node = child;
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class HeapMin {
    private final int[] array;
    private int size = 0;

    public HeapMin(int size) {
        array = new int[size + 2];
    }

    public void insert(int value) {
        array[++size] = value;
        swim(size);
    }

    public int pollMin() {
        int maxValue = array[1];
        swap(1, size--);
        sink(1);
        return maxValue;
    }

    public int peekMin() {
        return array[1];
    }

    public int getSize() {
        return size;
    }

    private void swim(int node) {
        while (node > 1 && array[node] < array[node / 2]) {
            swap(node, node / 2);
            node /= 2;
        }
    }

    private void sink(int node) {
        while (2 * node <= size) {
            int child = 2 * node;
            if (child < size && array[child] > array[child + 1])
                ++child;
            if (array[node] <= array[child])
                break;
            swap(node, child);
            node = child;
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
