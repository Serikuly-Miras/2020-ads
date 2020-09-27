package ru.mail.polis.ads.part1.Serikuly_Miras;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task3837 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        for (int i = 0; i < n; ++i) {
            char[] expression = in.next().toCharArray();
            String[] result = new String[expression.length];
            Arrays.fill(result, "");
            Index index = new Index(expression.length - 1);
            levelsOrderWalk(expression, index, 0, result);
            for (int k = expression.length - 1; k >= 0; --k)
                out.write(result[k]);
            out.write("\n");
            out.flush();
        }
    }

    public static void levelsOrderWalk(char[] expression, Index index, int depth, String[] result) {
        result[depth] += expression[index.value];
        --index.value;
        if (Character.isUpperCase(expression[index.value + 1])) {
            levelsOrderWalk(expression, index, depth + 1, result);
            levelsOrderWalk(expression, index, depth + 1, result);
        }
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

class Index {
    int value;

    public Index(int a) {
        value = a;
    }
}
