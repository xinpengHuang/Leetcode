```
/*
Description
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

Thoughts
Solution 1: O(n) time and O(1) space, using quickselect (standard quicksort).
Solution 2: O(n) time and O(1) space, using quickselect (three way quicksort).
*/

// Solution 1
class Solution {
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        return quickSelect(nums, nums.length - k, 0, nums.length - 1);
    }
    private int quickSelect(int[] nums, int k, int start, int end) {
        int i = partition(nums, start, end);  
        if (i > k) {
           return quickSelect(nums, k, start, i - 1);
        }
        if (i < k){
            return quickSelect(nums, k, i + 1, end);
        }
        return nums[i];
    }
    private int partition(int[] nums, int start, int end) {
        int cur = nums[start];
        int i = start + 1;
        int j = end;
        while (true) {
            while (i < end && nums[i] <= cur) {
                i++;
            }
            while (j > start && nums[j] >= cur) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, start, j);
        return j;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void shuffle(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int index = (int) (Math.random() * (i + 1));
            swap(nums, index, i);
        }
    }
}

// Solution 2
class Solution {
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        return quickSelect(nums, nums.length - k);
    }
    private int quickSelect(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int[] index = partition(nums, start, end); 
            if (index[0] > k) {
               end = index[0] - 1;
            } else if (index[1] < k){
                start = index[1] + 1;
            } else {
                return nums[index[0]];
            }
        }
        return -1;
    }
    private int[] partition(int[] nums, int start, int end) {
        int p = nums[start];
        int k = start;
        int i = start + 1;
        int j = end;
        while (i <= j) {
            int cmp = nums[i] - p;
            if (cmp > 0) {
                swap(nums, i, j);
                j--;
            } else if (cmp < 0) {
                swap(nums, k, i);
                k++;
                i++;
            } else {
                i++;
            }
        }
        return new int[] {k, j};
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void shuffle(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int index = (int) (Math.random() * (i + 1));
            swap(nums, index, i);
        }
    }
}
```
