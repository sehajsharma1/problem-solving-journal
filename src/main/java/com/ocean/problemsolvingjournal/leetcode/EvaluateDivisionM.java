package com.ocean.problemsolvingjournal.leetcode;

import java.util.*;

/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.



Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
*/
public class EvaluateDivisionM {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];

            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());

            graph.get(u).put(v, val);
            graph.get(v).put(u, 1.0 / val);
        }

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);

            if (!graph.containsKey(src) || !graph.containsKey(dest)) {
                result[i] = -1.0;
            } else if (src.equals(dest)) {
                result[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                result[i] = dfs(graph, src, dest, 1.0, visited);
            }
        }

        return result;
    }

    private double dfs(Map<String, Map<String, Double>> graph,
                       String curr,
                       String target,
                       double product,
                       Set<String> visited) {

        visited.add(curr);

        if (curr.equals(target)) {
            return product;
        }

        for (Map.Entry<String, Double> neighbor : graph.get(curr).entrySet()) {
            String next = neighbor.getKey();
            double value = neighbor.getValue();

            if (!visited.contains(next)) {
                double result = dfs(graph, next, target, product * value, visited);
                if (result != -1.0) {
                    return result;
                }
            }
        }

        return -1.0;
    }


    static class UnionFind {
        private Map<String, String> parent = new HashMap<>();
        private Map<String, Double> weight = new HashMap<>();

        public void add(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                weight.put(x, 1.0);
            }
        }

        public String find(String x) {
            if (!parent.get(x).equals(x)) {
                String origParent = parent.get(x);
                String root = find(origParent);

                // Path compression + weight update
                weight.put(x, weight.get(x) * weight.get(origParent));
                parent.put(x, root);
            }
            return parent.get(x);
        }

        public void union(String a, String b, double value) {
            add(a);
            add(b);

            String rootA = find(a);
            String rootB = find(b);

            if (!rootA.equals(rootB)) {
                parent.put(rootA, rootB);

                // Maintain equation a / b = value
                weight.put(rootA, value * weight.get(b) / weight.get(a));
            }
        }

        public double getRatio(String a, String b) {
            if (!parent.containsKey(a) || !parent.containsKey(b)) {
                return -1.0;
            }

            String rootA = find(a);
            String rootB = find(b);

            if (!rootA.equals(rootB)) {
                return -1.0;
            }

            return weight.get(a) / weight.get(b);
        }
    }

    /* Union-Find (Disjoint Set) Algo */
    public double[] calcEquationV1(List<List<String>> equations, double[] values, List<List<String>> queries) {

        UnionFind uf = new UnionFind();

        // Build
        for (int i = 0; i < equations.size(); i++) {
            uf.union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }

        // Query
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = uf.getRatio(queries.get(i).get(0), queries.get(i).get(1));
        }

        return result;
    }

    public static void main(String[] args) {

        EvaluateDivisionM sol = new EvaluateDivisionM();

        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("a","c"),
                Arrays.asList("d","e"),
                Arrays.asList("d","f"),
                Arrays.asList("a","d"),
                Arrays.asList("aa","bb"),
                Arrays.asList("aa","cc"),
                Arrays.asList("dd","ee"),
                Arrays.asList("dd","ff"),
                Arrays.asList("aa","dd"),
                Arrays.asList("a","aa")
        );

        double[] values = {
                2.0, 3.0, 4.0, 5.0, 7.0,
                5.0, 8.0, 9.0, 3.0, 2.0, 2.0
        };

        List<List<String>> queries = Arrays.asList(
                Arrays.asList("ff","a")
        );

        double[] result = sol.calcEquation(equations, values, queries);

        System.out.println("Output:");
        for (double r : result) {
            System.out.println(r);
        }
    }


}
