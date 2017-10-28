```
/*
Description
You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit.

Thoughts
Solution 1: O(n) time and O(1) space, reverse both lists, add step by step and reverse the res list. (cons: original list modified)
Solution 2: O(n) time and O(1) space, recursive solution. (to be completed)

*/

// Solution 1
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        return reverseList(addTwoNumbers_ReverseOrder(l1, l2));
    }
    private ListNode reverseList(ListNode head) {
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
    private ListNode addTwoNumbers_ReverseOrder(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null || l2 != null || value > 0) {
            if (l1 != null) {
                value += l1.val;
                l1 = l1.next; 
            }
            if (l2 != null) {
                value += l2.val;
                l2 = l2.next; 
            }
            ListNode temp = new ListNode(value % 10);
            value /= 10;
            cur.next = temp;
            cur = temp;
        }
        return dummy.next;
    }
}
```
