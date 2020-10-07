package ru.mail.polis.ads.part2.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7460668
public class Task1462 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int arraySize = in.nextInt();
        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; ++i) {
            array[i] = in.nextInt();
        }

        selectionSort(array, arraySize);

        for (int i = 0; i < arraySize; ++i) {
            out.print(array[i] + " ");
        }
    }

    private static void selectionSort(int[] array, int size) {
        for (int min = 0; min < size; min++) {
            int least = min;

            for (int j = min + 1; j < size; j++)
                if (isLess(array[j], array[least]))
                    least = j;

            int tmp = array[min];
            array[min] = array[least];
            array[least] = tmp;
        }
    }

    private static boolean isLess(int a, int b) {
        if (a % 10 < b % 10)
            return true;
        if (a % 10 == b % 10)
            return a < b;
        return false;
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
