```
/*
Description
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

Thoughts
Solution 1: O(n * log (n)) time and O(n) space, using binary search.
*/

// Solution 1
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<Integer>();       
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int j = Collections.binarySearch(list, nums[i]);
            j = j >= 0 ? j : -j - 1;
            if (j > list.size() - 1) {
                list.add(nums[i]);
            } else {
                list.set(j, nums[i]);
            }
        }
        return list.size();
    }
}
```
