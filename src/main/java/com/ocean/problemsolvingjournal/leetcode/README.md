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
