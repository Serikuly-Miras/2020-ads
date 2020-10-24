package ru.mail.polis.ads.part4.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Скобочная последовательность
// https://www.e-olymp.com/ru/submissions/7573492
public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        char[] sequence = in.reader.readLine().toCharArray();
        if (sequence.length == 0)
            return;

        int[][] dMatrix = new int[sequence.length][sequence.length];
        char[][][] correctSeq = new char[sequence.length][sequence.length][];
        for (int i = 0; i < sequence.length; ++i) {
            dMatrix[i][i] = 1;
            correctSeq[i][i] = getCorrectPair(sequence[i]);
        }

        for (int i = 1; i < sequence.length; ++i) {
            for (int j = i; j < sequence.length; ++j) {
                int gap = j - i;

                int min = dMatrix[gap][gap] + dMatrix[gap + 1][j];
                int kForMin = gap;
                for (int k = gap + 1; k < j; k++) {
                    int temp = dMatrix[gap][k] + dMatrix[k + 1][j];
                    if (min > temp) {
                        min = temp;
                        kForMin = k;
                    }
                }

                if (isCorrect(sequence[gap], sequence[j]) && min > dMatrix[gap + 1][j - 1]) {
                    dMatrix[gap][j] = dMatrix[gap + 1][j - 1];
                    if (correctSeq[gap + 1][j - 1] == null) {
                        correctSeq[gap][j] = new char[]{sequence[gap], sequence[j]};
                    } else {
                        char[] temp = merge(new char[]{sequence[gap]}, correctSeq[gap + 1][j - 1]);
                        correctSeq[gap][j] = merge(temp, new char[]{sequence[j]});
                    }
                } else {
                    dMatrix[gap][j] = min;
                    correctSeq[gap][j] = merge(correctSeq[gap][kForMin], correctSeq[kForMin + 1][j]);
                }
            }
        }
        out.print(correctSeq[0][sequence.length - 1]);
    }

    private static char[] getCorrectPair(char symbol) {
        return (symbol == '(' || symbol == ')') ?
                new char[]{'(', ')'} : new char[]{'[', ']'};
    }

    private static char[] merge(char[] first, char[] second) {
        char[] result = new char[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    private static boolean isCorrect(char left, char right) {
        return left == '(' && right == ')' || left == '[' && right == ']';
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
