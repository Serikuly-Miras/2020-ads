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

// (Кратчайший путь) https://www.e-olymp.com/ru/problems/4856
// https://www.e-olymp.com/ru/submissions/7984550
public class Task4 {
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

        int start = in.nextInt() - 1;
        int target = in.nextInt() - 1;

        int from, to, weight;
        for (int i = 0; i < m; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            weight = in.nextInt();

            adjacencyLists.get(from).add(new WeightedEdge(to, weight));
            adjacencyLists.get(to).add(new WeightedEdge(from, weight));
        }

        int[] weights = new int[n];
        int[] predecessor = new int[n];
        Arrays.fill(weights, Integer.MAX_VALUE);
        Arrays.fill(predecessor, -1);
        weights[start] = 0;

        boolean changed;
        int newWeight;
        for (int i = 0; i < n - 1; i++) {
            changed = false;

            for (int j = 0; j < n; j++) {
                for (WeightedEdge edge : adjacencyLists.get(j)) {
                    if (weights[j] == Integer.MAX_VALUE) continue;
                    newWeight = weights[j] + edge.weight;
                    if (weights[edge.vertex] > newWeight) {
                        weights[edge.vertex] = newWeight;
                        predecessor[edge.vertex] = j;
                        changed = true;
                    }
                }
            }

            if (!changed) break;
        }

        out.println(weights[target]);

        int[] path = new int[n];
        int counter = 0;
        path[counter++] = target;
        while (predecessor[target] != -1) {
            target = predecessor[target];
            path[counter++] = target;
        }

        for (int i = counter - 1; i >= 0; --i) {
            out.print((path[i] + 1) + " ");
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
