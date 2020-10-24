package ru.mail.polis.ads.part4.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Мыш (кродеться)
// https://www.e-olymp.com/ru/submissions/7573491
public class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();   // rows
        int n = in.nextInt();   // columns
        int[][] tiles = new int[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                tiles[i][j] = in.nextInt();
            }
        }

        int[][] dMatrix = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 1; j <= n; ++j) {
                // to find max in left and bottom
                dMatrix[i][j] = Math.max(dMatrix[i + 1][j], dMatrix[i][j - 1]) + tiles[i][j - 1];
            }
        }

        // recover path
        int yIndex = 0; // row
        int xIndex = n; // column
        StringBuilder builder = new StringBuilder();
        while (xIndex != 1 && yIndex != m) {
            if (dMatrix[yIndex][xIndex] == tiles[yIndex][xIndex - 1] + dMatrix[yIndex][xIndex - 1]) {
                builder.append("R");
                --xIndex;
            } else {
                builder.append("F");
                ++yIndex;
            }

            if (yIndex == m) {
                while (xIndex > 1) {
                    builder.append("R");
                    --xIndex;
                }
            } else if (xIndex == 1) {
                while (yIndex < m - 1) {
                    builder.append("F");
                    ++yIndex;
                }
            }
        }

        out.print(builder.reverse().toString());
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
