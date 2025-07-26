package hr.code.java;

public class CommonChild {
    int lcs(String X, String Y, int m, int n) {
        if (m == -1 || n == -1)
            return 0;
        if (X.charAt(m) == Y.charAt(n))
            return 1 + lcs(X, Y, m - 1, n - 1);
        else
            return max(lcs(X, Y, m, n - 1),
                    lcs(X, Y, m - 1, n));
    }

    // Utility function to get max of 2 integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Driver code
    public static void main(String[] args) {
        CommonChild lcs
                = new CommonChild();
        String S1 = "ABCD";
        String S2 = "ABDC";
        int m = S1.length()-1;
        int n = S2.length()-1;

        System.out.println("Length of LCS is"
                + " " + lcs.lcs(S1, S2, m, n));
    }
}
