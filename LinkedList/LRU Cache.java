```
/*
Description
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Thoughts
Solution 1: O(1) time for get and put operation, using doubly linked list and hashmap.
*/

// Solution 1
class LRUCache {
	Node dummyFirst;
	Node dummyLast;
	Map<Integer, Node> map;
	int capacity;
	private class Node {
		int key;
		int val;
		Node next;
		Node pre;
        	Node(){}
		Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
    
	public LRUCache(int capacity) {
		dummyFirst = new Node();
		dummyLast = new Node();
		dummyFirst.next = dummyLast;
		dummyLast.pre = dummyFirst;
		map = new HashMap<Integer, Node>(capacity);
		this.capacity = capacity;
   	}

	public int get(int key) {
		Node cur = getNode(key);
		if (cur == null) {
			return -1;
		}
		return cur.val;
	}
    
	private Node getNode(int key) {
		Node cur = map.get(key);
		if (cur == null) {
			return null;
		}
		remove(cur);
		insertFront(cur);
		return cur;
	}

	public void put(int key, int value) {
		Node cur = getNode(key);
        
        	// key exists
		if (cur != null) {
		    cur.val = value;
		    map.put(key, cur);
		    return;
		}
        
		// no such key
		if (map.size() == capacity) {
		    Node temp = remove(dummyLast.pre);
		    map.remove(temp.key);
		}
		cur = new Node(key, value);
		insertFront(cur);
		map.put(key, cur);
	}

	private Node remove(Node cur) {
		Node pre = cur.pre;
		Node next = cur.next;
		pre.next = next;
		next.pre = pre;
		cur.pre = null;
		cur.next = null;
		return cur;
	}

	private void insertFront(Node cur) {
		Node next = dummyFirst.next;
		dummyFirst.next = cur;
		cur.pre = dummyFirst;
		cur.next = next;
		next.pre = cur;
	}
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

```
