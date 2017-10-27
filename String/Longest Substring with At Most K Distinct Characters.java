```
/*
Description
Given a string, find the length of the longest substring T that contains at most k distinct characters.
For example, Given s = “eceba” and k = 2, T is "ece" which its length is 3.

Thoughts
Solution 1: O(N) time and O(1) space, using sliding window with two pointers 
Solution 2: O(N) time and O(k) space, using sliding window with two pointers and LinkedHashMap
*/

// Solution 1
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

// Solution 2
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) {
            return 0;
        }
        // record the last location for each character in the sliding window
        // arrange them in an access-ordered LinkedHashMap
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>(k + 1, (float) 0.75, true);
        int start = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i)) && map.size() == k) {
                // the first entry given from the iterator is the eldest entry
                Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
                int value = iterator.next().getValue();
                start = value + 1;
                iterator.remove();
            }
            map.put(s.charAt(i), i);
            length = Math.max(length, i - start + 1);
        }
        return length;
    }
}
```
