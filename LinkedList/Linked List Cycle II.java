```
/*
Description
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Thoughts
Solution 1: O(n) time and O(1) space, using two pointers.
*/

// Solution 1
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (slow == quick) {
                break;
            }
        }
        
        // no circle
        if (quick.next == null || quick.next.next == null) {
            return null;
        }
        
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}
```
