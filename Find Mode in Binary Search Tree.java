```
/*
Description
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? 
(Assume that the implicit stack space incurred due to recursion does not count).

Thoughts
Solution 1: O(n) time and O(1) space (not counting stack space due to recursion), using two pass inorder traversal.
*/

// Solution 1
class Solution {
    public int[] findMode(TreeNode root) {
        int[] maxCount = new int[1];
        int[] modeCount = new int[1];
        int[] index = new int[1];
        inorder(root, null, maxCount, modeCount, index);
        int[] res = new int[modeCount[0]];
        inorder(root, res, maxCount, modeCount, index);
        return res;
    }
    private void inorder(TreeNode cur, int[] res, int[] maxCount, int[] modeCount, int[] index) {
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
        TreeNode pre = new TreeNode(Integer.MAX_VALUE);
        int count = 0;
        while (cur != null || !deque.isEmpty()) {
            while (cur != null) {
                deque.push(cur);
                cur = cur.left;
            }
            cur = deque.pop();
            if (cur.val != pre.val) {
                count = 0;
            }
            count++;
            if (count > maxCount[0]) {
                maxCount[0] = count;
                modeCount[0] = 1;
            } else if (count == maxCount[0]) {
                if (res == null) {
                    modeCount[0]++;
                } else {
                    res[index[0]++] = cur.val;
                }
            }
            pre = cur;
            cur = cur.right;
        }
    }
}
```
