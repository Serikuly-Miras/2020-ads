package ru.mail.polis.ads.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// (Циклы в графе) https://www.e-olymp.com/ru/problems/2022
public class Task2 {
    static int answer;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> adjacencyLists = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencyLists.add(new ArrayList<>());
        }

        int from, to;
        for (int i = 0; i < m; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            adjacencyLists.get(from).add(to);
            adjacencyLists.get(to).add(from);
        }

        answer = n;
        boolean[] visited = new boolean[n];
        boolean[] cycle = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                DFS(adjacencyLists, i, -1, visited, cycle);
            }
        }

        out.print(answer == n ? "No\n" : "Yes\n" + (answer + 1));
    }

    private static void DFS(List<List<Integer>> adjacencyLists, int vertex, int prev, boolean[] visited, boolean[] cycle) {
        cycle[vertex] = true;

        for (int edge : adjacencyLists.get(vertex)) {
            if (edge != prev) {
                if (cycle[edge] && answer > edge) {
                    answer = edge;
                }

                if (!visited[edge] && !cycle[edge]) {
                    DFS(adjacencyLists, edge, vertex, visited, cycle);
                }
            }
        }

        cycle[vertex] = false;
        visited[vertex] = true;
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
