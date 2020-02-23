import java.util.Stack;

class LC255 {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null) return false;
        
        Stack<Integer> stack = new Stack();
        
        int low = Integer.MIN_VALUE;
        
        for (int n : preorder) {
            if (n < low) {
                return false;
            }
            
            while (!stack.isEmpty() && n > stack.peek()) {
                low = stack.pop();
            }
                   
            stack.push(n);
        }
                   
        return true;
    }
}