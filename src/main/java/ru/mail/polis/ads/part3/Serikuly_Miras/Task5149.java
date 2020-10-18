package ru.mail.polis.ads.part3.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7536705
public class Task5149 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] cords = new int[n];
        for (int i = 0; i < n; ++i)
            cords[i] = in.nextInt();

        int left = 0;
        int right = cords[n - 1] - cords[0] + 1;
        while (right - left > 1) {
            int mid = (right + left) / 2;
            if (checkX(cords, mid, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        out.print(left);
    }

    private static boolean checkX(int[] cords, int x, int k) {
        int counter = 1;
        int lastCord = cords[0];
        for (int i = 0; i < cords.length; ++i) {
            if (cords[i] - lastCord >= x) {
                lastCord = cords[i];
                ++counter;
            }
        }
        return counter >= k;
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
