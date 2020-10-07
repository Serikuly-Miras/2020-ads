package ru.mail.polis.ads.part2.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7466319
public class Task4827 {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int k = in.nextInt();
        String[] rawInput = in.reader.readLine().split(" ");
        int arraySize = rawInput.length;
        BigInteger[] array = new BigInteger[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = new BigInteger(rawInput[i]);
        }

        BigInteger result = findOrderStatistic(array, arraySize - k);
        out.println(result);
    }

    private static BigInteger findOrderStatistic(BigInteger[] array, int order) {
        int left = 0;
        int right = array.length - 1;

        while (true) {
            int mid = hoarePartition(array, left, right);
            if (mid == order) {
                return array[mid];
            } else if (order < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    private static int hoarePartition(BigInteger[] array, int left, int right) {
        BigInteger pivot = array[left + (int) (Math.random() * (right - left - 1))];

        while (left <= right) {
            while (array[left].compareTo(pivot) < 0) {
                ++left;
            }

            while (array[right].compareTo(pivot) > 0) {
                --right;
            }

            if (left >= right) {
                break;
            }
            BigInteger temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
        return right;
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
