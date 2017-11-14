```
/*
Description
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

Thoughts
Solution 1: O(n) time and O(n) space, using bucket sort.
*/

// Solution 1
class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        
        /* 
            The lower bound of maximum gap is k = (max - min) / (n - 1). We can then build m buckets with width 
            equals k (or less than k),
            [min, min + k), ..., [min + (m - 1) * k, min + m * k), where max < min + m * k.
            As long as we make sure the maximum gap in each bucket is less than k, the maximum gap can then only be 
            achieved with elements in different buckets.
            One exception is that k == 0. For this case, we build buckets with width = 1, and if we cannot achieve 
            any gap with elements in different buckets. It means all the elements are in one bucket and they share 
            the same value.
        */
        
        int k = Math.max((max - min) / (n - 1), 1); 
        int m = (max - min) / k + 1;
        int[][] buckets = new int[2][m];
        Arrays.fill(buckets[0], Integer.MAX_VALUE); // min of each bucket
        Arrays.fill(buckets[1], Integer.MIN_VALUE); // max of each bucket
        for (int i = 0; i < n; i++) {
            int index = (nums[i] - min) / k;
            buckets[0][index] = Math.min(buckets[0][index], nums[i]);
            buckets[1][index] = Math.max(buckets[1][index], nums[i]);
        }      
        int res = Integer.MIN_VALUE;
        int pre = buckets[1][0];
        for (int i = 1; i < m; i++) {
            if (buckets[1][i] == Integer.MIN_VALUE) {
                continue;
            }
            if (pre == Integer.MIN_VALUE) {
                pre = buckets[1][i];
                continue;
            }
            res = Math.max(res, buckets[0][i] - pre);
            pre = buckets[1][i];
        }
        return res == Integer.MIN_VALUE ? 0 : res;
    }
}
```
