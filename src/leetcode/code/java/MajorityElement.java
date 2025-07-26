package leetcode.code.java;

/*Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.*/
class MajorityElement {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2, 1, 1}));
    }

    public static int majorityElement(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int number = nums[i];
            int count = 0;
            if (number != Integer.MAX_VALUE) {
                for (int j = i; j < size; j++) {
                    if (number == nums[j] && number != Integer.MAX_VALUE) {
                        ++count;
                        nums[j] = Integer.MAX_VALUE;
                    }
                }
                if (count > (size / 2)) {
                    return number;
                }
            }
        }
        return 0;
    }

    /* Moore Voting Algorithm
    The algorithm starts by assuming the first element as the majority candidate and sets the count to 1.
    As it iterates through the array, it compares each element with the candidate:
    a. If the current element matches the candidate, it suggests that it reinforces the majority element because it appears again. Therefore, the count is incremented by 1.
    b. If the current element is different from the candidate, it suggests that there might be an equal number of occurrences of the majority element and other elements. Therefore, the count is decremented by 1.
    Note that decrementing the count doesn't change the fact that the majority element occurs more than n/2 times.
    If the count becomes 0, it means that the current candidate is no longer a potential majority element. In this case, a new candidate is chosen from the remaining elements.
    The algorithm continues this process until it has traversed the entire array.
    The final value of the candidate variable will hold the majority element.
    */
    public int majorityElement1(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
