```
/*
Description
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Thoughts
Solution 1: O(1) time and O(n) space, using two stacks.
*/

// Solution 1
class MinStack {
    Stack<Integer> sData;
    Stack<Integer> sMin;
    /** initialize your data structure here. */
    public MinStack() {
        sData = new Stack<Integer>();
        sMin = new Stack<Integer>();
    }
    
    public void push(int x) {
        sData.push(x);
        if (sMin.isEmpty() || x <= sMin.peek()) {
            sMin.push(x);
        }
    }
    
    public void pop() {
        int res = sData.pop();
        if (res == sMin.peek()) {
            sMin.pop();
        }
    }
    
    public int top() {
        return sData.peek();
    }
    
    public int getMin() {
        return sMin.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```
