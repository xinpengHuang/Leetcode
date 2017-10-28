```
/*
Description
Given an n-element array of integers and an integer, m, determine the maximum value of the sum of any of its subarrays modulo m.

Thoughts
Solution 1: O(n * log m) time and O(m) space, using TreeSet to store prefix sum.
*/

// Solution 1
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long maximumSum(long[] a, long m) {
        // Complete this function
        TreeSet<Long> set = new TreeSet<Long>();
        long res = 0;
        long sum = 0;
        set.add((long) 0);
        for (int i = 0; i < a.length; i++) {
            sum = (a[i] % m + sum) % m;
            set.add(sum);
            Long cur = set.higher(sum);
            res = Math.max(res, cur == null ? sum : sum + m - cur);
            if (res == m - 1) {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            long m = in.nextLong();
            long[] a = new long[n];
            for(int a_i = 0; a_i < n; a_i++){
                a[a_i] = in.nextLong();
            }
            long result = maximumSum(a, m);
            System.out.println(result);
        }
        in.close();
    }
}

```
