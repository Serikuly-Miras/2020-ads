package ru.mail.polis.ads.part5.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

// Квадратный корень
// https://www.e-olymp.com/ru/submissions/7576512
public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        double x = Double.parseDouble(in.reader.readLine());

        double left = 0;
        double right = x;
        double mid;
        double result = 0;

        while (Math.abs(x - result) > 0.0000001) {
            mid = (right + left) / 2;
            result = Math.pow(mid, 2) + Math.sqrt(mid);
            if (x >= result) {
                left = mid;
            } else {
                right = mid;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
