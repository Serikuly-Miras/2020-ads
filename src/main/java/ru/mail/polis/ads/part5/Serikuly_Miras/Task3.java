package ru.mail.polis.ads.part5.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

// Наибольшая последовательно кратная подпоследовательность
// https://www.e-olymp.com/ru/submissions/7577584
public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        }

        int[] dArray = new int[n];
        for (int i = 0; i < n; ++i) {
            int maxSequenceLenght = 1;
            for (int j = 0; j < i; ++j) {
                if (array[j] == 0) continue;
                if (array[i] % array[j] == 0 && dArray[j] >= maxSequenceLenght) {
                    maxSequenceLenght = dArray[j] + 1;
                }
            }
            dArray[i] = maxSequenceLenght;
        }

        int max = 1;
        for (int val : dArray) {
            if (max < val) {
                max = val;
            }
        }

        out.print(max);
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
