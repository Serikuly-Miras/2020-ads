package ru.mail.polis.ads.part1.Serikuly_Miras;

import java.io.*;
import java.util.StringTokenizer;

public class Task5327 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String brackeys = in.next();
        int brackeysCounter = 0;

        for (char symbol : brackeys.toCharArray()) {
            if (symbol == '(') {
                ++brackeysCounter;
            } else if (symbol == ')') {
                --brackeysCounter;
                if (brackeysCounter < 0)
                    break;
            }
        }

        out.write(brackeysCounter == 0 ? "YES" : "NO");
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