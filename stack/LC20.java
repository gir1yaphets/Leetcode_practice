class LC20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        
        char[] ca = s.toCharArray();
        
        for (char c : ca) {
            //遇到左括号直接进stack，遇到右括号判断栈顶是否是可以匹配的左括号，注意栈顶可能为null
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty() || map.get(c) != stack.peek()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        
        return stack.isEmpty();
    }
}