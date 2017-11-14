```
/*
Description
Given an integer array, return the k-th smallest distance among all the pairs. 
The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
Thoughts
Solution 1: O(n * (log(n) + log(m))) time and O(1) space, using binary search and two pointers. (m = max aboslute difference, up to 1000000)
*/

// Solution 1
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        // since low should never be a possible result, it is set to -1 instead of 0.
        int low = -1;
        int high = nums[nums.length - 1] - nums[0];
        while (low + 1 < high) {
            int mid = (high - low) / 2 + low;
            int count = 0;
            int j = 0;
            for (int i = 1; i < nums.length; i++) {
                while (j < i) {
                    if (nums[i] - nums[j] <= mid) {
                        break;
                    }
                    j++;
                }
                count += i - j;
            }
            
            // if count equals k, which means that the number of distances(<= mid) is exactly k, there are two cases:
            // case 1: 
            //     mid is a valid distance and thus mid will be the result.
            // case 2: 
            //     mid is not a valid distance, then the maximum of all those distances less than mid will be the result.
            //     In this case, since result will be less than mid, we need to set mid as higher limit. 
            if (count >= k) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return high;
    }
    
}

```
