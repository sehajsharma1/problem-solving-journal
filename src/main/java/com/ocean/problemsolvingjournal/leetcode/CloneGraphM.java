package com.ocean.problemsolvingjournal.leetcode;

import java.util.*;

/*
Given a reference of a node in a connected undirected graph.
Return a deep copy (clone) of the graph.
Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
class Node {
    public int val;
    public List<Node> neighbors;
}

Test case format:
For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

Example 1:
Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
*/
public class CloneGraphM {
    private Map<Node, Node> map = new HashMap<>();

    static class Node {
        public int val;
        public List<Node> neighbors;

        // Constructors
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }


    // ⭐ Optimal DFS
    public Node cloneGraph(Node node) {

        if (node == null) return null;

        // already cloned
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // clone current node
        Node clone = new Node(node.val);
        map.put(node, clone);

        // clone neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }

    public Node cloneGraphV1(Node node) {

        if (node == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        Node clone = new Node(node.val);
        map.put(node, clone);
        queue.offer(node);

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            for (Node neighbor : curr.neighbors) {

                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }

                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }

        return clone;
    }

    // 🔹 Main method to test
    public static void main(String[] args) {

        // Create sample graph manually
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        CloneGraphM solution = new CloneGraphM();

        Node clonedGraph = solution.cloneGraphV1(node1);

        System.out.println("Graph cloned successfully: " + (clonedGraph != null));
    }

}
