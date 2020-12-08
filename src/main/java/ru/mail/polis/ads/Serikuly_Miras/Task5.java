package ru.mail.polis.ads.Serikuly_Miras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// (Кратчайший путь) https://www.e-olymp.com/ru/problems/4853
// https://www.e-olymp.com/ru/submissions/7984564
public class Task5 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<List<Integer>> adjacencyLists = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjacencyLists.add(new ArrayList<>());
        }

        int start = in.nextInt() - 1;
        int target = in.nextInt() - 1;

        int from, to;
        for (int i = 0; i < m; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            adjacencyLists.get(from).add(to);
            adjacencyLists.get(to).add(from);
        }

        int[] distance = new int[n];
        int[] predecessor = new int[n];
        Arrays.fill(distance, -1);
        Arrays.fill(predecessor, -1);

        BFS(adjacencyLists, start, distance, predecessor);
        if (predecessor[target] == -1) {
            out.println("-1");
        } else {
            out.println(distance[target]);

            int[] path = new int[n];
            int depth = 0;
            path[depth++] = target;
            while (predecessor[target] != -1) {
                target = predecessor[target];
                path[depth++] = target;
            }

            for (int i = depth - 1; i >= 0; --i) {
                out.print((path[i] + 1) + " ");
            }
        }
    }

    private static void BFS(List<List<Integer>> adjacencyLists, int start, int[] distance, int[] predecessor) {
        distance[start] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int edge : adjacencyLists.get(vertex)) {
                if (distance[edge] == -1) {
                    queue.add(edge);
                    distance[edge] = distance[vertex] + 1;
                    predecessor[edge] = vertex;
                }
            }
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
