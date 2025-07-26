package leetcode.code.java;

public class JumpGame2 {
    public static void main(String[] args) {
        int[] jump = new int[]{2, 3, 1};
        System.out.println(Jump(jump));
    }

    public static int Jump(int[] nums) {
        int curEnd = 0;
        int cufFarthest = 0;
        int jumpStep = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            cufFarthest = Math.max(cufFarthest, nums[i] + i);
            if (i == curEnd) {
                jumpStep++;
                curEnd = cufFarthest;
            }
            if (curEnd >= nums.length - 1) break;

        }


        return jumpStep;
    }
}
