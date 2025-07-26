package leetcode.code.java;
/*Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.*/
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(arr));
    }
    public static int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        int maxleft = 0, maxright = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= maxleft) {
                    maxleft = height[left];
                } else {
                    res += maxleft - height[left];
                }
                left++;
            } else {
                if (height[right] >= maxright) {
                    maxright = height[right];
                } else {
                    res += maxright - height[right];
                }
                right--;
            }
        }
        return res;
    }
}
