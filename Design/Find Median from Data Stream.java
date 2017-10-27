```
/*
Description
Median is the middle value in an ordered integer list. If the size of the list is even
, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.

Thoughts
Solution 1: O(lgN) time for addNum() and O(1) time for findMedian(), using two heaps.
*/

// Solution 1
class MedianFinder {
    Queue<Integer> maxQ = new PriorityQueue<Integer>();
    Queue<Integer> minQ = new PriorityQueue<Integer>();
    
    public void addNum(int num) {
        if (maxQ.size() == 0 || num >= minQ.peek()) {
            minQ.offer(num);
        }
        else {
            maxQ.offer(-num);
        }
        if (minQ.size() - maxQ.size() > 1) {
            maxQ.offer(-minQ.poll());
        }
        if (minQ.size() - maxQ.size() < -1) {
            minQ.offer(-maxQ.poll());
        }
    }
    
    public double findMedian() {
        if (minQ.size() > maxQ.size()) {
            return (double) minQ.peek();
        }
        if (minQ.size() < maxQ.size()) {
            return (double) -maxQ.peek();
        }
        return ((double) -maxQ.peek() + minQ.peek()) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```
