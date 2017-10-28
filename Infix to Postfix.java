```
/*
Description
You have to ease the task for Mac by transforming a given Infix expression to Postfix.

Instead of numeric variables the expression given is using lowercase english letters (a to z). 
The priority of operators is as follows '+', '-', '*', '/', '^' (increasing left to right) '^' is for exponentiation.
Each case would be a string without space in valid form. 
There won't be any case with ambiguity i.e., such that two different answers are posible e.g., (a+b+c), 
it would be well perenthesised in that case.

Input
First line containing a number T, number of test cases. Then T lines with test cases in form of strings (say S).

Constraints
1 <= T <= 1000
1<= length of S <= 1000 
Output

For each expression of T given test cases print the output postfix form in new line.

Sample Input
4
(a+b)
(a+b*c)
(r+(s+t))
((r+s)+t)

Sample Output
ab+
abc*+
rst++
rs+t+

Thoughts
Solution 1: O(n) time and space, using two stacks.
*/

// Solution 1
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (scanner.hasNext()) {
            System.out.println(InfixToPostfix(scanner.next()));
        }
    }
    public static String InfixToPostfix(String s) {
        Deque<String> nums = new ArrayDeque<String>();
        Deque<Character> symbols = new ArrayDeque<Character>();
        Map<Character, Integer> order = new HashMap<Character, Integer>(5);
        order.put('+', 1);
        order.put('-', 2);
        order.put('*', 3);
        order.put('/', 4);
        order.put('^', 5);
        
        char[] c = s.toCharArray();
        int i = 0;
        while (i < c.length) {
            if (order.containsKey(c[i])) {
                while (!symbols.isEmpty() && symbols.peek() != '(' && order.get(symbols.peek()) >= order.get(c[i])) {
                    update(nums, symbols, order);
                }
                symbols.push(c[i]);
            } else if (c[i] == '('){
                symbols.push(c[i]);
            } else if (c[i] == ')') {
                while (symbols.peek() != '(') {
                    update(nums, symbols, order);
                }
                symbols.pop();
            } else {
                nums.push(new String(c, i, 1));
            }
            i++;
        }
        while (symbols.size() > 0) {
            update(nums, symbols, order);
        }
        return nums.isEmpty() ? "" : nums.pop();
    }
    public static void update(Deque<String> nums, Deque<Character> symbols, Map<Character, Integer> order) {
        String second = nums.pop();
        String first = nums.pop();
        char symbol = symbols.pop();
        nums.push(first + second + symbol);
    }
}
```
