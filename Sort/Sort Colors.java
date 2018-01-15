```
/*
Description
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Thoughts
Solution 1 for 3 colors: O(n) time and O(1) space, using 3 pointers.
Solution 2 for 4 colors: O(n) time and O(1) space, using 4 pointers.
*/

// Solution 1
class Solution {
    public void sortColors(int[] nums) {
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 0) {
                exch(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else {
                exch(nums, j, k);
                k--;
            } 
        }
    }
    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/* Solution 2
    0           1           2             3
0 ~ i - 1   i ~ j - 1   k - 1 ~ l   l - 1 ~ n - 1
j and k always point at unscanned elements.
*/
class Solution {
    public void sortColors(int[] nums) {
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        int l = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 0) {
                exch(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else if (nums[j] == 2) {
                exch(nums, j, k);
                k--;
            } else {
                exch(nums, k, l);
                k--;
                if (j > k) { // corner case, no need to swap if currently j is larger than k
                    break;
                }
                exch(nums, j, l);
                l--;
            }
        }
    }
    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```
