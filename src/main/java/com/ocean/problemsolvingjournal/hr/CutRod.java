package com.ocean.problemsolvingjournal.hr;

public class CutRod {
    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 5, 8, 9};
        int n = 4;
        System.out.println(cutRod(array, n));
        System.out.println(memoizedCutRod(array, n));
        System.out.println(bottomUpCutRod(array, n));
        extendedBottomUpCutRod(array, n);
    }

    //Recursive top-down implementation
    public static int cutRod(int[] p, int n) {
        if (n == 0) {
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, p[i] + cutRod(p, n - i));
        }
        return q;

    }

    //Recursive top-down implementation with memoization
    public static int memoizedCutRod(int[] p, int n) {
        int[] r = new int[n + 1];
        for (int rr = 0; rr <= n; rr++) {
            r[rr] = Integer.MIN_VALUE;
        }
        return memoizedCutRodAux(p, n, r);
    }

    public static int bottomUpCutRod(int[] p, int n) {
        int[] r = new int[n + 1];
        r[0] = 0;
        int q;
        for (int j = 1; j <= n; j++) {
            q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                q = Math.max(q, p[i] + r[j - i]);
            }
            r[j] = q;

        }
        return r[n];
    }

    public static void extendedBottomUpCutRod(int[] p, int n) {
        int[] r = new int[n + 1];
        int[] s = new int[n + 1];
        r[0] = 0;
        int q;
        for (int j = 1; j <= n; j++) {
            q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                if (q < p[i] + r[j - i]) {
                    q = p[i] + r[j - i];
                    s[j] = i;
                }
            }
            r[j] = q;
        }
        while (n > 0) {
            System.out.print(s[n]);
            System.out.print(" ");
            n = n - s[n];
        }
    }

    public static int memoizedCutRodAux(int[] p, int n, int[] r) {
        if (r[n] >= 0)
            return r[n];
        int q;
        if (n == 0) {
            q = 0;
        } else {
            q = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                q = Math.max(q, p[i] + memoizedCutRodAux(p, n - i, r));
            }
        }
        r[n] = q;
        return q;

    }


}
