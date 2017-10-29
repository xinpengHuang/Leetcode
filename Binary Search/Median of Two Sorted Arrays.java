```
/*
Description
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

Thoughts
Solution 1: O(log(k)) time and O(1) space for finding kth smallest, using binary search.
*/

// Solution 1
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1.length + nums2.length) % 2 == 1) {
            return (double) findKthSmallest(nums1, nums2, 0, 0, (nums1.length + nums2.length) / 2 + 1);
        } else {
            int left = findKthSmallest(nums1, nums2, 0, 0, (nums1.length + nums2.length) / 2);
            int right = findKthSmallest(nums1, nums2, 0, 0, (nums1.length + nums2.length) / 2 + 1);
            return (double) (left + right) / 2;
        }
    }
    private int findKthSmallest(int[] nums1, int[] nums2, int start1, int start2, int k) {
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        } else if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        } 
        
        if (k == 1) {         
            return Math.min(nums1[start1], nums2[start2]);
        }
        
        int mid1 = start1 + k / 2 - 1;
        int mid2 = start2 + k / 2 - 1;
        
        if (mid1 >= nums1.length) {
            mid1 = nums1.length - 1;
            mid2 = start2 + k - (nums1.length - start1) - 1;
        } else if (mid2 >= nums2.length) {
            mid2 = nums2.length - 1;
            mid1 = start1 + k - (nums2.length - start2) - 1;
        }
        
        if (nums1[mid1] <= nums2[mid2]) {
            return findKthSmallest(nums1, nums2, mid1 + 1, start2, k - (mid1 - start1 + 1));
        } else {
            return findKthSmallest(nums1, nums2, start1, mid2 + 1, k - (mid2 - start2 + 1));            
        }
    }
}
```
