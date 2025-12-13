Leetcode:

---

### 📌 Prefix Marking Technique

* Mark each interval with `+1` at the start and `-1` at `(end + 1)` in an auxiliary array.
* Compute a prefix sum over the array — any region where the sum `> 0` represents covered (merged) interval space.
* A transition `0 → >0` indicates the start of a merged interval, and `>0 → 0` marks its end.
* Best used when the numeric range is known and reasonably small, making it memory-efficient.

### 📌 **Formula Used (Exponentiation by Squaring)**

- If `n < 0`: `x^n = 1 / x^{-n}`
- If `n` is even: `x^n = (x^{n/2})^2`
- If `n` is odd: `x^n = x * x^{n-1}`

XOR cancels duplicates because a ^ a = 0, so all paired numbers become zero and only the unique number remains.

---

