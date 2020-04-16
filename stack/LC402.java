class LC402 {
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0 || k == 0) return num;
        if (k == num.length()) return "0";
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < num.length(); i++) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            
            stack.push(num.charAt(i));
        }
        
        //避免11111这种都相等remove不掉 或者112 k=1 直接把2也push进来
        while(k > 0){
            stack.pop();
            k--;            
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        sb = sb.reverse();
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
    
        return sb.toString();
    }
}