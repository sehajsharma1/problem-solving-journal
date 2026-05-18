package com.ocean.problemsolvingjournal.leetcode;

import java.util.*;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
*/
public class CourseSchedule2M {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];

            graph.get(prereq).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[index++] = current;

            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (index == numCourses) {
            return result;
        }

        return new int[0];
    }

    public int[] findOrderV1(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build graph
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            graph.get(prereq).add(course);
        }

        int[] visited = new int[numCourses]; // 0,1,2
        List<Integer> result = new ArrayList<>();

        // Run DFS for each node
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (!dfs(i, graph, visited, result)) {
                    return new int[0]; // cycle found
                }
            }
        }

        // Reverse result for topological order
        Collections.reverse(result);

        // Convert to array
        return result.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int node, List<List<Integer>> graph, int[] visited, List<Integer> result) {

        if (visited[node] == 1) return false; // cycle
        if (visited[node] == 2) return true;  // already processed

        // Mark as visiting
        visited[node] = 1;

        for (int neighbor : graph.get(node)) {
            if (!dfs(neighbor, graph, visited, result)) {
                return false;
            }
        }

        // Mark as visited
        visited[node] = 2;

        // Add to result (post-order)
        result.add(node);

        return true;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0},{3,1},{3,2}};
        CourseSchedule2M solver = new CourseSchedule2M();
        int[] result = solver.findOrderV1(numCourses, prerequisites);
        System.out.println("canFinish = " + result);
    }
}
