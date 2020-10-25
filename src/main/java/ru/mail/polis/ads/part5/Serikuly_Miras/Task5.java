package ru.mail.polis.ads.part5.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

// Перестановки
// https://www.e-olymp.com/ru/submissions/7576579
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 1; i <= n; i++) {
            array[i - 1] = i;
        }

        for (int j : array) {
            out.print(j + " ");
        }
        out.println();

        if (n > 1) {
            while (true) {
                int tail = array.length - 1;
                int swap = array.length;
                while (tail != 0 && array[tail] < array[tail - 1]) {
                    --tail;
                }
                if (tail == 0) break;

                for (int i = array.length - 1; i >= tail; --i) {
                    if (array[tail - 1] < array[i]) {
                        swap = i;
                        break;
                    }
                }

                swap(array, tail - 1, swap);

                reverse(array, tail, array.length - 1);

                for (int j : array) {
                    out.print(j + " ");
                }
                out.println();
            }
        }
    }

    private static void reverse(int[] array, int left, int right) {
        while (right - left > 0) {
            swap(array, left, right);
            --right;
            ++left;
        }
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
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
