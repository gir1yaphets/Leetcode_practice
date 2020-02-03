import java.util.*;

/**
 * 用队列实现栈
 */
class LC225 {

    private Queue<Integer> main;
    private Queue<Integer> backup;
    private int top;

    /** Initialize your data structure here. */
    public LC225() {
        main = new LinkedList<>();
        backup = new LinkedList<>();
        top = 0;
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        main.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (main.size() > 1) {
            int top = main.poll();
            backup.add(top);
        }

        int last = main.poll();

        /**
         * 当main将其最后一个元素remove以后，main就空了，这个时候直让main的引用再指回备份的queue，这样总维持main是有数据的queue
         */
        Queue<Integer> temp = main;
        main = backup;
        backup = temp;

        return last;
    }
    
    /** Get the top element. */
    public int top() {
        return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return main.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */