class LC1249 {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) return s;
        
        int open = 0;
        StringBuilder sb = new StringBuilder();
        
        //先移除非法的右括号
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open += 1;
            } else if (c == ')') {
                if (open == 0) {
                    continue;
                }
                open -= 1;
            }
            sb.append(c);
        }

        StringBuilder res = new StringBuilder();
        
        //从后往前移除多余的左括号
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '(' && open-- > 0) {
                continue;
            }
            res.append(sb.charAt(i));
        }
        
        return res.reverse().toString();
    }
}