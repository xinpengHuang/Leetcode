
'''

/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.
*/

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int start = 0;
        int length = 0;
        int total = 0;
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)] == 0) {
                total++;
            }
            count[s.charAt(i)]++;
            while (total > k) {
                count[s.charAt(start)]--;
                if (count[s.charAt(start)] == 0) {
                    total--;
                }
                start++;
            }
            length = Math.max(length, i - start + 1);
        }
        return length;
    }
}
'''
