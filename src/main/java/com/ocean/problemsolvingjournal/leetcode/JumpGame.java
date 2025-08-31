package leetcode.code.java;

/*You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.*/
public class JumpGame {

    public static void main(String[] args) {
        int[] jump = new int[]{0};
        System.out.println(canJump(jump));
    }

    public static boolean canJump(int[] nums) {
        int gas = 0;
        for (int i = 0; i < nums.length; i++) {
            if (gas < 0) {
                return false;
            } else if (nums[i] > gas) {
                gas = nums[i];
            }
            --gas;
        }
        return true;
    }
}
