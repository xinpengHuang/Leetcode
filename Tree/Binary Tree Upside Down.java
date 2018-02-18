```
/*
Description
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
   
Thoughts
Solution 1: O(n) time and O(1) space, using recursion.
*/

// Solution 1
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return dfs(root, null, null);
    }
    private TreeNode dfs(TreeNode root, TreeNode father, TreeNode sibling) {
        if (root == null) {
            return null;
        }
        TreeNode res = dfs(root.left, root, root.right);
        root.left = sibling;
        root.right = father;
        return res == null ? root : res;
    }
}
```
