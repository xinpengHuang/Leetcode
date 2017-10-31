```
/*
Description
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

Thoughts
Solution 1: O(n * log (n) * k) time and O(n * k) space. (k is average number of digits of nums)
*/

// Solution 1
class Solution {
    public String largestNumber(int[] nums) {
        String[] sorted = new String[nums.length];
        boolean sign = false;
        for (int i = 0; i < nums.length; i++) {
            sorted[i] = String.valueOf(nums[i]);
            if (nums[i] > 0) {
                sign = true;
            }
        }
        if (!sign) {
            return "0";
        }
        Arrays.sort(sorted, new Comparator<String>() {
            public int compare(String a, String b) {
                String longString = a;
                String shortString = b;
                int shortNum = Integer.parseInt(b);
                if (a.length() < b.length()) {
                    longString = b;
                    shortString = a;
                    shortNum = Integer.parseInt(a);
                }
                int i = shortString.length();
                while (i < longString.length()) {
                    int longNum = Integer.parseInt(longString.substring(i - shortString.length(), i));;
                    if (longNum == shortNum) {
                        i += shortString.length();
                        continue;
                    }
                    return a.length() >= b.length() ? longNum - shortNum : shortNum - longNum;
                }
                int res = Integer.parseInt(longString.substring(i - shortString.length(), longString.length()) 
                                           + shortString.substring(0, i - longString.length())) - shortNum;
                return a.length() >= b.length() ? res : -res;
                
            }
        });
        StringBuilder builder = new StringBuilder();
        for (int i = sorted.length - 1; i >= 0; i--) {
            builder.append(sorted[i]);
        }
        return builder.toString();
    }
}
```
