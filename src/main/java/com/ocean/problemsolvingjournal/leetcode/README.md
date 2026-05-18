Leetcode:

### 📌 Prefix Marking Technique

* Mark each interval with `+1` at the start and `-1` at `(end + 1)` in an auxiliary array.
* Compute a prefix sum over the array — any region where the sum `> 0` represents covered (merged) interval space.
* A transition `0 → >0` indicates the start of a merged interval, and `>0 → 0` marks its end.
* Best used when the numeric range is known and reasonably small, making it memory-efficient.

### 📌 **Formula Used (Exponentiation by Squaring)**

- If `n < 0`: `x^n = 1 / x^{-n}`.
- If `n` is even: `x^n = (x^{n/2})^2`.
- If `n` is odd: `x^n = x * x^{n-1}`.

XOR cancels duplicates because a ^ a = 0, so all paired numbers become zero and only the unique number remains.

Two intervals [a₁, a₂] and [b₁, b₂] overlap iff a₁ ≤ b₂ and a₂ ≥ b₁.

<p align="left">
  <img src="images/DFS vs BFS.png" alt="DFS vs BFS" width="400" />
</p>

Preorder  : Visit Root → Traverse Left → Traverse Right.

Inorder   : Traverse Left → Visit Root → Traverse Right.

Postorder : Traverse Left → Traverse Right → Visit Root.

Backtracking is an algorithmic problem-solving technique where a solution is built step by step by trying all possible choices, and whenever a choice leads to an invalid state or a completed solution, the algorithm reverses (backtracks) that choice to explore other options. For example, while generating all letter combinations for a phone number like "23", the algorithm picks 'a' for 2, then tries 'd', 'e', 'f' for 3 to form "ad", "ae", "af", and after each combination, it removes the last letter and tries the next one, ensuring all valid combinations are explored efficiently.

backtrack(state):
if state is a valid solution:
record answer
return

    for each possible choice:
        make the choice
        backtrack(new state)
        undo the choice

Try → Recurse → Backtrack

(r / 3) * 3 + (c / 3) gives the index (0–8) of the 3×3 Sudoku box a cell belongs to, where rows choose the box row and columns choose the box column.

```md
## 8 Directions in a 2D Grid

int[] rowDir = {-1, -1, -1, 0, 0, 1, 1, 1};
int[] colDir = {-1, 0, 1, -1, 1, -1, 0, 1};

Each cell in a 2D array has 8 neighbors (horizontal, vertical, and diagonal).
The direction arrays store relative row and column offsets: `-1` (up/left), `0` (same), `+1` (down/right).
Combining these offsets gives all adjacent cells except the center `(0,0)`.
`newRow = row + rowDir[i]` and `newCol = col + colDir[i]` move to each neighbor.
This pattern is widely used in DFS/BFS grid problems.
```

```md
Kadane's Algorithm:
Keep adding numbers while the sum is positive.
If it becomes negative, restart — and remember the best sum found.
```
```md
Union-Find (Disjoint Set Union) is a data structure used to efficiently manage and track connected components in a graph. It supports two main operations: find (to identify the group of an element) and union (to merge two groups). With optimizations like path compression and union by rank/size, it achieves nearly constant time complexity. It is widely used in problems involving connectivity, cycle detection, and minimum spanning trees.
```

```md
Kahn’s Algorithm is a BFS-based approach to perform Topological Sort using indegree.
It processes nodes with indegree 0 and gradually removes dependencies from the graph.
If all nodes are processed, the graph is acyclic; otherwise, a cycle exists.
```
```md
Rule of DP (VERY IMPORTANT)
If current state depends on future → go right → left
If depends on past → go left → right
```