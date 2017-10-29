```
/*
Description
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution and you may not use the same element twice.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

Thoughts
Solution 1: O(n) time and O(1) space, using two pointers.
*/

// Solution 1
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0, j = nums.length - 1; i < j;) {
            if (nums[i] + nums[j] > target) {
                j--;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                return new int[] {i + 1, j + 1};
            }
        }
        return new int[2];
    }
}
```
