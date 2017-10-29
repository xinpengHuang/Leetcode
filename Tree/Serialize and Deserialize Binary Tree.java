```
/*
Description
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Thoughts
Solution 1: O(n) time and O(?) space, using recursion.
*/

// Solution 1
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        recursiveSerialize(root, builder);
        builder.deleteCharAt(0);
        return builder.toString();
    }
    private void recursiveSerialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append(",#");
            return;
        }
        builder.append(",");
        builder.append(root.val);
        recursiveSerialize(root.left, builder);
        recursiveSerialize(root.right, builder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        char[] c = data.toCharArray();
        return recursiveDeserialize(c, new int[1]);
    }
    private TreeNode recursiveDeserialize(char[] c, int[] cur) {
        int i = cur[0];
        if (i >= c.length) {
            return null;
        }
        if (c[i] == '#') {
            cur[0] += 2;
            return null;
        }
        int value = 0;
        boolean sign = true;
        if (c[i] == '-') {
            sign = false;
            i++;
        }
        while (i < c.length && c[i] != ',') {
            value *= 10;
            value += Character.getNumericValue(c[i]);
            i++;
        }
        value = sign ? value : -value;
        cur[0] = i + 1;
        TreeNode root = new TreeNode(value);
        root.left = recursiveDeserialize(c, cur);
        root.right = recursiveDeserialize(c, cur);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```
