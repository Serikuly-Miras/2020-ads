package ru.mail.polis.ads.part2.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7460666
public class Task3738 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int arraySize = in.nextInt();
        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; ++i) {
            array[i] = in.nextInt();
        }

        radixSort(array, arraySize);

        for (int i = 0; i < arraySize; ++i) {
            out.print(array[i] + " ");
        }
    }

    private static void radixSort(int[] array, int size) {
        int maxInArray = getMax(array, size);

        for (int exp = 1; maxInArray / exp > 0; exp *= 10)
            countSort(array, size, exp);
    }

    private static void countSort(int[] array, int n, int exp) {
        int[] output = new int[n];
        int[] counters = new int[10];
        Arrays.fill(counters, 0);

        int i;
        for (i = 0; i < n; ++i)
            counters[(array[i] / exp) % 10]++;

        for (i = 1; i < 10; ++i)
            counters[i] += counters[i - 1];

        for (i = n - 1; i >= 0; --i) {
            output[counters[(array[i] / exp) % 10] - 1] = array[i];
            counters[(array[i] / exp) % 10]--;
        }

        for (i = 0; i < n; ++i)
            array[i] = output[i];
    }

    private static int getMax(int[] array, int size) {
        int max = array[0];
        for (int i = 1; i < size; ++i)
            if (array[i] > max)
                max = array[i];
        return max;
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
