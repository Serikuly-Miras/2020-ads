package ru.mail.polis.ads.part5.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

// Шаблон и слово
// https://www.e-olymp.com/ru/submissions/7577708
public class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String firstLine = in.reader.readLine();
        String secondLine = in.reader.readLine();

        boolean[][] dMatrix = new boolean[firstLine.length() + 1][secondLine.length() + 1];
        dMatrix[0][0] = true;

        for (int i = 1; i < dMatrix.length; ++i) {
            for (int j = 1; j < dMatrix[0].length; ++j) {
                if (firstLine.charAt(i - 1) == secondLine.charAt(j - 1)
                        || firstLine.charAt(i - 1) == '?' || secondLine.charAt(j - 1) == '?') {
                    dMatrix[i][j] = dMatrix[i - 1][j - 1];
                }

                if (firstLine.charAt(i - 1) == '*' || secondLine.charAt(j - 1) == '*') {
                    dMatrix[i][j] = dMatrix[i - 1][j] || dMatrix[i][j - 1] || dMatrix[i - 1][j - 1];
                }
            }
        }

        System.out.println(dMatrix[dMatrix.length - 1][dMatrix[0].length - 1] ? "YES" : "NO");
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
