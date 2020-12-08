package ru.mail.polis.ads.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// (Форд-Беллман) https://www.e-olymp.com/ru/problems/1453
// https://www.e-olymp.com/ru/submissions/7984532
public class Task3 {
    private static class WeightedEdge {
        int vertex;
        int weight;

        public WeightedEdge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<WeightedEdge>> adjacencyLists = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencyLists.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            adjacencyLists.get(in.nextInt() - 1).add(new WeightedEdge(in.nextInt() - 1, in.nextInt()));
        }

        int[] weights = new int[n];
        Arrays.fill(weights, 30_000);
        weights[0] = 0;

        boolean chaged;
        int newWeight;
        for (int i = 0; i < n - 1; i++) {
            chaged = false;

            for (int j = 0; j < n; j++) {
                for (WeightedEdge edge : adjacencyLists.get(j)) {
                    if (weights[j] == 30_000) continue;
                    newWeight = weights[j] + edge.weight;
                    if (weights[edge.vertex] > newWeight) {
                        weights[edge.vertex] = newWeight;
                        chaged = true;
                    }
                }
            }

            if (!chaged) break;
        }

        for (int i = 0; i < n; i++) {
            out.print(weights[i] + " ");
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
