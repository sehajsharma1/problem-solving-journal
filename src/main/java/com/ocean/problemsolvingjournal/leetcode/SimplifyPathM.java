package com.ocean.problemsolvingjournal.leetcode;
/*
You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.
The rules of a Unix-style file system are as follows:

A single period '.' represents the current directory.
A double period '..' represents the previous/parent directory.
Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
The simplified canonical path should follow these rules:

The path must start with a single slash '/'.
Directories within the path must be separated by exactly one slash '/'.
The path must not end with a slash '/', unless it is the root directory.
The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
Return the simplified canonical path.

Example 1:
Input: path = "/home/"
Output: "/home"
*/
public class SimplifyPathM {
    public String simplifyPath(String path) {
        int n = path.length();
        String[] stack = new String[n];
        int top = 0;
        int i = 0;

        while (i < n) {
            // skip '/'
            while (i < n && path.charAt(i) == '/') i++;

            if (i == n) break;

            int start = i;
            while (i < n && path.charAt(i) != '/') i++;

            String dir = path.substring(start, i);

            if (dir.equals(".")) {
                continue;
            }
            else if (dir.equals("..")) {
                if (top > 0) top--;
            }
            else {
                stack[top++] = dir;
            }
        }

        if (top == 0) return "/";

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < top; j++) {
            sb.append('/').append(stack[j]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPathM solver = new SimplifyPathM();
        String[] tests = {
                "/......"
        };

        for (String t : tests) {
            System.out.println(t + " -> " + solver.simplifyPath(t));
        }
    }
}
