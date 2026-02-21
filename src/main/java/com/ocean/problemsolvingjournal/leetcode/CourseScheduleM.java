package com.ocean.problemsolvingjournal.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
*/

public class CourseScheduleM {

    private Map<Integer, List<Integer>> adj;

    public CourseScheduleM(int n) {
        adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }
    }

    // add directed edge u -> v
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    private boolean dfs(int node, int[] state) {
        if (state[node] == 1) return true;   // cycle
        if (state[node] == 2) return false;  // already safe

        state[node] = 1; // visiting

        for (int nei : adj.get(node)) {
            if (dfs(nei, state)) return true;
        }

        state[node] = 2; // done
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        for (int[] arr : prerequisites) {
            addEdge(arr[0], arr[1]);
        }

        int[] state = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, state)) {
                return false; // cycle found
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};
        CourseScheduleM solver = new CourseScheduleM(numCourses);
        boolean result = solver.canFinish(numCourses, prerequisites);
        System.out.println("canFinish = " + result);
    }
}
