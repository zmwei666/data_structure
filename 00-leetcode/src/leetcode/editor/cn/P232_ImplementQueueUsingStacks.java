//请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）： 
//
// 实现 MyQueue 类： 
//
// 
// void push(int x) 将元素 x 推到队列的末尾 
// int pop() 从队列的开头移除并返回元素 
// int peek() 返回队列开头的元素 
// boolean empty() 如果队列为空，返回 true ；否则，返回 false 
// 
//
// 说明： 
//
// 
// 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法
//的。 
// 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：
//["MyQueue", "push", "push", "peek", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 1, 1, false]
//
//解释：
//MyQueue myQueue = new MyQueue();
//myQueue.push(1); // queue is: [1]
//myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
//myQueue.peek(); // return 1
//myQueue.pop(); // return 1, queue is [2]
//myQueue.empty(); // return false
// 
//
// 
// 
//
// 
//
// 提示： 
//
// 
// 1 <= x <= 9 
// 最多调用 100 次 push、pop、peek 和 empty 
// 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作） 
// 
//
// 
//
// 进阶： 
//
// 
// 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。 
// 
//
// Related Topics 栈 设计 队列 👍 1057 👎 0


package leetcode.editor.cn;

import java.util.Stack;

/**
 * 用栈实现队列
 * @author CodeTheory
 * @date 2024-02-27 11:05:27
 */
public class P232_ImplementQueueUsingStacks{
	 public static void main(String[] args) {
         MyQueue myQueue = new P232_ImplementQueueUsingStacks().new MyQueue();
         myQueue.push(1);
         myQueue.push(2);
         System.out.println(myQueue.peek());
         System.out.println(myQueue.pop());
         myQueue.push(3);
         myQueue.push(4);
         System.out.println(myQueue.peek());
         System.out.println(myQueue.pop());
         System.out.println(myQueue.peek());
         System.out.println(myQueue.pop());
         System.out.println(myQueue.peek());
         System.out.println(myQueue.pop());
         System.out.println(myQueue.empty());
     }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class MyQueue {
    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();
    public MyQueue() {

    }
    
    public void push(int x) {
        in.push(x);
    }
    
    public int pop() {
        if (!out.isEmpty()) {
            return out.pop();
        }
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        return out.pop();
    }
    
    public int peek() {
        if (!out.isEmpty()) {
            return out.peek();
        }
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        return out.peek();
    }
    
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}