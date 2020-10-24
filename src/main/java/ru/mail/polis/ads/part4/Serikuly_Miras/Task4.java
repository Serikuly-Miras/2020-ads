package ru.mail.polis.ads.part4.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Лесенка
// https://www.e-olymp.com/ru/submissions/7573488
public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] numbers = new int[n + 2];
        for (int i = 0; i < n; ++i) {
            numbers[i + 1] = in.nextInt();
        }

        int stepMaxDistance = in.nextInt();
        int[] dynamic = new int[n + 2];
        for (int i = 1; i < n + 2; ++i) {
            int maxInRange = Integer.MIN_VALUE;

            for (int j = i - 1; j >= 0 && j >= i - stepMaxDistance; --j) {
                if (dynamic[j] > maxInRange) {
                    maxInRange = dynamic[j];
                }
            }
            dynamic[i] = maxInRange + numbers[i];
        }

        out.println(dynamic[n + 1]);
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
