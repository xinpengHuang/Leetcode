```
/*
Description
You’re given the pointer to the head node of a linked list and a specific position. Counting backwards from the tail node of the linked list, get the value of the node at the given position. A position of 0 corresponds to the tail, 1 corresponds to the node before the tail and so on.

Constraints 
Position will be a valid element in linked list.

Output Format 
Find the node at the given position counting backwards from the tail. Then return the data contained in this node. Do NOT print anything to stdout/console.

Sample Input

1 -> 3 -> 5 -> 6 -> NULL, positionFromTail = 0
1 -> 3 -> 5 -> 6 -> NULL, positionFromTail = 2

Thoughts
Solution 1: O(n) time and O(1) space, using recursion.
*/

// Solution 1
/*
  Get Nth element from the end in a linked list of integers
  Number of elements in the list will always be greater than N.
  Node is defined as 
  class Node {
     int data;
     Node next;
  }
*/
    
int GetNode(Node head,int n) {
     // This is a "method-only" submission. 
     // You only need to complete this method. 
    return recur(head, n, new int[1]).data;
}
Node recur(Node head, int n, int[] id) {
    if (head == null) {
        id[0] = -1;
        return null;
    }
    Node node = recur(head.next, n, id);
    id[0]++;
    if (id[0] == n) {
        return head;
    }
    return node;
}



```
