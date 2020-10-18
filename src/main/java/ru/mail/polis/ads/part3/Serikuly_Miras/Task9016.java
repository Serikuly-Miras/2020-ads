package ru.mail.polis.ads.part3.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7534434
public class Task9016 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        }

        for (int i = 0; i < q; ++i) {
            out.println(isContains(in.nextInt(), array) ? "YES" : "NO");
        }
    }

    private static boolean isContains(int target, int[] array) {
        int left = 0;
        int right = array.length - 1;
        boolean result = false;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == array[mid]) {
                result = true;
                break;
            }
            if (target < array[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return result;
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
