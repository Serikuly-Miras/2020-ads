package ru.mail.polis.ads.part4.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Наибольшая общая подпоследовательность
// https://www.e-olymp.com/ru/submissions/7573490
public class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int rows = in.nextInt();
        int[] first = new int[rows];
        for (int i = 0; i < rows; ++i) {
            first[i] = in.nextInt();
        }

        int columns = in.nextInt();
        int[] second = new int[columns];
        for (int i = 0; i < columns; ++i) {
            second[i] = in.nextInt();
        }

        int[][] dMatrix = new int[rows + 1][columns + 1];
        for (int i = 1; i < rows + 1; ++i) {
            for (int j = 1; j < columns + 1; ++j) {
                if (first[i - 1] == second[j - 1]) {
                    dMatrix[i][j] = dMatrix[i - 1][j - 1] + 1;
                } else {
                    dMatrix[i][j] = Math.max(dMatrix[i - 1][j], dMatrix[i][j - 1]);
                }
            }
        }

        out.print(dMatrix[rows][columns]);
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
