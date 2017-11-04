```
/*
Description
We are given N different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.

You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.

Example 1:

Input:

["with", "example", "science"], "thehat"
Output:

3

Thoughts

Solution 1: O(2 ^ m * n) time and O(2 ^ m + n) space, using dynamic programming.
*/

// Solution 1
class Solution {
    public int minStickers(String[] stickers, String target) {
        // count target
        int[] targetCount = new int[26];
        for (int i = 0; i < target.length(); i++) {
            targetCount[target.charAt(i) - 'a']++;
        }
        
        // count candidates and discard useless ones
        List<int[]> counts = new ArrayList<int[]>();
        for (String sticker : stickers) {
            int[] count = new int[26];
            boolean isUseful = false;
            for (char c : sticker.toCharArray()) {
                if (targetCount[c - 'a'] > 0) {
                    count[c - 'a']++;
                    isUseful = true;
                }
            }
            if (isUseful) {
                counts.add(count);
            }
        }
        
        // memorialize those Strings whose minimum numbers we have calculated.
        Map<String, Integer> states = new HashMap<String, Integer>();
        states.put("", 0);
        
        int res = search(counts, states, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private int search(List<int[]> counts, Map<String, Integer> states, String target) {
        Integer res = states.get(target);
        if (res != null) {
            return res;
        }
        res = Integer.MAX_VALUE;
        int[] targetCount = new int[26];
        for (int i = 0; i < target.length(); i++) {
            targetCount[target.charAt(i) - 'a']++;
        }
        for (int[] count : counts) {
            if (count[target.charAt(0) - 'a'] == 0) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                for (int k = 0; k < Math.max(0, targetCount[i] - count[i]); k++) {
                    builder.append((char) ('a' + i));
                }
            }
            int nums = search(counts, states, builder.toString());
            res = nums == Integer.MAX_VALUE ? res : Math.min(res, 1 + nums);
        }
        states.put(target, res);
        return res;
    }
}
```
