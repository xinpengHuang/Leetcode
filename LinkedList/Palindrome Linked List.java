```
/*
Description
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?

Thoughts
Solution 1: O(N) time and O(N) stack space, using recursion.
Solution 2: O(N) time and O(1) space, using list reversion.
*/

// Solution 1
class Solution {
    public boolean isPalindrome(ListNode head) {
        int len = findLength(head);
        boolean[] sign = new boolean[1];
        sign[0] = true;
        isPalindrome(head, len, sign);
        return sign[0];
    }
    
    private int findLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
    
    private ListNode isPalindrome(ListNode cur, int len, boolean[] sign) {
        if (len == 0) {
            return cur;
        }
        if (len == 1) {
            return cur.next;
        }
        ListNode x = isPalindrome(cur.next, len - 2, sign);
        if (x.val != cur.val) {
            sign[0] = false; 
        }
        x = x.next;
        return x;
    }
}

// Solution 2
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode mid = findMiddle(head);
        if (mid == null) {
            return true;
        }
        ListNode first = reverse(mid.next);
        return compare(head, first);
    }
    
    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode first = null;
        ListNode second = head;
        while (second != null) {
            ListNode temp = second;
            second = second.next;
            temp.next = first;
            first = temp;
        }
        return first;
    }
    
    private boolean compare(ListNode x, ListNode y) {
        while (x != null && y != null) {
            if (x.val != y.val) {
                return false;
            }
            x = x.next;
            y = y.next;
        }
        return true;
    }
}
```
