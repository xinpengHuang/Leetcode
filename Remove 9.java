```
/*
Description
Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...
So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...
Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

Thoughts
Solution 1: O(1) time and space, using base 9.
*/

// Solution 1
public class Solution {
    public int newInteger(int n) {
        int res = 0;
        int i = 0;
        while (n > 0) {
            res += n % 9 * Math.pow(10, i);
            n /= 9;
            i++;
        }
        return res;
    }
}
```
