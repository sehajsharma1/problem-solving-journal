package hr.code.java;

/*Genes can be represented as a string of length n (where n is divisible by 4), composed of the letters A, C, T, and G.

It is considered to be steady if each of the four letters occurs exactly n/4 times.

For example, GACT and AAGTGCCT are both steady genes.*/
public class BearAndSteadyGene {

    public static void main(String[] args) {
        String s = "ACTGAAAG";
        System.out.println(steadyGene(s));
    }

    public static int steadyGene(String gene) {

        // Write your code here
        String s = gene;
        String genes = "ATGC";
        int n = s.length();
        int[] cnt = new int[4];
        int left = 0;
        for (int i = 0; i < n; i++) {
            int cur = genes.indexOf(s.charAt(i));
            if (cnt[cur] + 1 > n / 4) {
                left = i - 1;
                break;
            }
            cnt[cur]++;
        }
        if (left == 0) {
            return 0;
        }
        int res = n;
        int right = n - 1;
        for (int i = left; i >= 0; i--) {
            int cur;
            while (right > 0) {
                cur = genes.indexOf(s.charAt(right));
                if (cnt[cur] + 1 > n / 4) {
                    break;
                }
                cnt[cur]++;
                right--;
            }
            cur = genes.indexOf(s.charAt(i));
            cnt[cur]--;
            res = Math.min(res, right - i);
        }
        return res;

    }
}