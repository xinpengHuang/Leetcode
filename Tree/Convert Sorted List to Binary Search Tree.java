```
/*
Description
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

Thoughts
Solution 1: O(n) time and O(?) space, using recursion.
*/

// Solution 1
public class Solution {
    ListNode cur;
    public TreeNode sortedListToBST(ListNode head) {
        int n = getLength(head);
        cur = head;
        return build(n);
    }
    private int getLength(ListNode head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }
    private TreeNode build(int n) { // build n nodes under this root
        if (n == 0) {
            return null;
        }
        TreeNode left = build(n / 2);
        TreeNode root = new TreeNode(cur.val);
        cur = cur.next;
        TreeNode right = build(n - n / 2 - 1);
        root.left = left;
        root.right = right;
        return root;
    }
}
```
