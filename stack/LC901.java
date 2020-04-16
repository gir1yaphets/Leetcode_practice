import java.util.*;

class LC901 {
    private Stack<Integer> stack;
    private Map<Integer, Integer> map;

    public LC901() {
        map = new HashMap<>();
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int count = 1;
        while (!stack.isEmpty() && stack.peek() <= price) {
            int p = stack.pop();
            count += map.get(p);
        }

        map.put(price, count);
        stack.push(price);
        return count;
    }

    private Stack<int[]> stackArray;
    
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next_array(int price) {
        int count = 1;
        while (!stackArray.isEmpty() && stackArray.peek()[0] <= price) {
            count += stackArray.pop()[1];
        }

        stackArray.push(new int[]{price, count});
        
        return count;
    }
}