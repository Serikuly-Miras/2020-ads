package ru.mail.polis.ads.part5.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

// Дипломы
// https://www.e-olymp.com/ru/submissions/7576566
public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        long width = in.nextInt();
        long height = in.nextInt();
        long quantity = in.nextInt();

        long left = Math.max(width, height);
        long right = left * quantity;
        long mid;
        while (left < right) {
            mid = (right + left) / 2;
            long contains = (mid / width) * (mid / height);
            if (quantity <= contains) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        out.print(left);
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
