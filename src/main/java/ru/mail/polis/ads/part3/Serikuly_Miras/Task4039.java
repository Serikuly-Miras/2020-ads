package ru.mail.polis.ads.part3.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7534157
public class Task4039 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap();
        for (int i = in.nextInt(); i > 0; --i){
            switch (in.nextInt()){
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.delMax());
                    break;
            }
        }
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
}

class Heap {
    private final List<Integer> list;
    private int size;

    public Heap() {
        list = new ArrayList<Integer>();
        this.list.add(0);
        size = 0;
    }

    public Integer delMax() {
        Integer max = list.get(1);
        swap(1, size--);
        sink(1);
        return max;
    }

    public void insert(Integer value) {
        size++;
        if (size <= list.size() - 1)
            list.set(size, value);
        else
            list.add(value);
        swim(size);
    }

    private void swim(int node) {
        while (node > 1 && list.get(node) > list.get(node / 2)) {
            swap(node, node / 2);
            node /= 2;
        }
    }

    private void sink(int node) {
        while (2 * node <= size) {
            int child = node * 2;
            if (child < size && list.get(child) < list.get(child + 1))
                ++child;
            if (list.get(node) >= list.get(child))
                break;
            swap(node, child);
            node = child;
        }
    }

    private void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
