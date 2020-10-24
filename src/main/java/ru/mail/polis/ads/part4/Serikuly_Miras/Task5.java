package ru.mail.polis.ads.part4.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Количество инверсий
// https://www.e-olymp.com/ru/submissions/7573500
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        out.print(countInv(array, 0, n - 1));
    }

    private static int countInv(int[] array, int left, int right) {
        if (right <= left) {
            return 0;
        }
        int mid = (right + left) / 2;
        return countInv(array, left, mid) + countInv(array, mid + 1, right) + countSplitInv(array, left, mid, right);
    }

    private static int countSplitInv(int[] array, int left, int mid, int right) {
        int leftInRightHalf = mid + 1;
        int leftInLeftHalf = left;
        int invCounter = 0;

        int[] result = new int[right - left + 1];
        int resultIndex = 0;

        while (leftInLeftHalf <= mid && leftInRightHalf <= right) {
            if (array[leftInLeftHalf] <= array[leftInRightHalf]) {
                result[resultIndex++] = array[leftInLeftHalf++];
            } else {
                result[resultIndex++] = array[leftInRightHalf++];
                invCounter += (mid - leftInLeftHalf + 1);
            }
        }

        while (leftInLeftHalf <= mid) {
            result[resultIndex++] = array[leftInLeftHalf++];
        }

        while (leftInRightHalf <= right) {
            result[resultIndex++] = array[leftInRightHalf++];
        }

        System.arraycopy(result, 0, array, left, result.length);
        return invCounter;
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
