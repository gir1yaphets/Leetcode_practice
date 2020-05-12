import java.util.*;
class LC301 {
    private List<String> res = new ArrayList<>();
    
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        //计算出左边右边最少需要移除的括号数量
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        dfs(s, 0, left, right, 0, "");
        
        return res;
    }
    
    private void dfs(String s, int start, int left, int right, int open, String sol) {
        if (left < 0 || right < 0 || open < 0) {
            return;
        }
        
        if (start == s.length()) {
            if (left == 0 && right == 0 && open == 0) {
                if (!res.contains(sol)) {
                    res.add(sol);
                }
            }
            return;
        }
            
        char c = s.charAt(start);

        if (c == '(') {
            dfs(s, start + 1, left, right, open + 1, sol + '('); //不移除左括号
            dfs(s, start + 1, left - 1, right, open, sol); //移除一个左括号
        } else if (c == ')') {
            dfs(s, start + 1, left, right, open - 1, sol + ')');
            dfs(s, start + 1, left, right - 1, open, sol);
        } else {
            dfs(s, start + 1, left, right, open, sol + c);
        }
    }
}