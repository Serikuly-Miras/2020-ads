package ru.mail.polis.ads.part2.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7460664
public class Task4037 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int arraySize = in.nextInt();
        int[][] robots = new int[arraySize][2];

        for (int i = 0; i < arraySize; ++i) {
            robots[i][0] = in.nextInt(); // Main number
            robots[i][1] = in.nextInt(); // Secondary number
        }

        robots = mergeSort(robots, 0, arraySize - 1);

        for (int[] robot : robots) {
            out.println(robot[0] + " " + robot[1]);
        }
    }

    private static int[][] mergeSort(int[][] array, int left, int right) {
        if (left == right) {
            return new int[][]{array[left]};
        }

        int mid = (left + right) / 2;
        return merge(mergeSort(array, left, mid), mergeSort(array, mid + 1, right));
    }

    private static int[][] merge(int[][] first, int[][] second) {
        int firstSize = first.length;
        int secondSize = second.length;
        int[][] result = new int[firstSize + secondSize][2];

        int i = 0, j = 0;
        while (i < firstSize && j < secondSize) {
            if (first[i][0] <= second[j][0]) {
                result[i + j] = first[i];
                i++;
            } else {
                result[i + j] = second[j];
                j++;
            }
        }

        while (i < firstSize) {
            result[i + j] = first[i];
            ++i;
        }

        while (j < secondSize) {
            result[i + j] = second[j];
            ++j;
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
