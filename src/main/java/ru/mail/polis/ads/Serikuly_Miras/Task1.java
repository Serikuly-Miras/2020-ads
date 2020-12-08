package ru.mail.polis.ads.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// (Топологическая сортировка) https://www.e-olymp.com/ru/problems/1948
// https://www.e-olymp.com/ru/submissions/7984487
public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> adjacencyLists = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencyLists.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            adjacencyLists.get(in.nextInt() - 1).add(in.nextInt() - 1);
        }

        boolean[] visited = new boolean[n];
        boolean[] cycle = new boolean[n];
        Stack<Integer> accumulator = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (!DFS(adjacencyLists, i, visited, cycle, accumulator)) {
                    out.println("-1");
                    return;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            out.print(accumulator.pop() + " ");
        }
        out.println();
    }

    private static boolean DFS(List<List<Integer>> adjacencyLists, int vertex, boolean[] visited, boolean[] cycle, Stack<Integer> accumulator) {
        cycle[vertex] = true;
        for (int edge : adjacencyLists.get(vertex)) {
            if (cycle[edge]) {
                return false;
            }
            if (!visited[edge] && !DFS(adjacencyLists, edge, visited, cycle, accumulator)) {
                return false;
            }
        }
        cycle[vertex] = false;
        visited[vertex] = true;
        accumulator.push(vertex + 1);
        return true;
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
