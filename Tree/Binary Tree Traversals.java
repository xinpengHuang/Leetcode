```

/*
Description
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

Thoughts
Solution 1: O(n) time and O(n) space, using queue.
*/

// Solution 1


/*
Description
Given a binary tree, return the preorder traversal of its nodes' values.

Thoughts
Solution 1: O(n) time and O(n) space, using stack.
*/

// Solution 1
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        List<Integer> res = new LinkedList<Integer>();
        TreeNode cur = root;
        while (cur != null || !deque.isEmpty()) {
            if (cur != null) {
                res.add(cur.val);
                deque.push(cur);
                cur = cur.left;
            } else {
                cur = deque.pop();
                cur = cur.right;
            }
        }
        return res;
    }
}

/*
Description
Given a binary tree, return the inorder traversal of its nodes' values.

Thoughts
Solution 1: O(n) time and O(n) space, using stack.
*/

// Solution 1
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        List<Integer> res = new LinkedList<Integer>();
        TreeNode cur = root;
        while (cur != null || !deque.isEmpty()) {
            if (cur != null) { // key point: only push left children of every right child
                deque.push(cur);
                cur = cur.left;
            }
            else {
                cur = deque.pop();
                res.add(cur.val);
                cur = cur.right; // cur can only be every right child
            }
        }
        return res;
    }
}
```
