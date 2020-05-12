import java.util.*;
public class LC32 {
    /**
     * dp[i]是到长度为i为止，最长有效parenthesis的substring的长度
     * 如果是右括号并且之前有打开的左括号可以匹配，dp[i] = dp[i-1] + 2
     * 同时dp[i]还需要加上dp[i-dp[i]]，因为dp[i]是到i为止消耗掉未匹配的左括号的长度，还需要加上这个连续匹配长度之前能够匹配的长度
     * take example "()(())"
        i : [0,1,2,3,4,5]
        s : [( ,) ,( ,( ,) ,) ]
        DP:[0,2,0,0,2,6]
        当i=5的时候,首先dp[i] = dp[i-1] + 2 = 4,表示从上一个打开的左括号到当前位置能够匹配的是4个
        这时候还需要加上这个4个之前能够匹配的长度，即dp[i-dp[i]]
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int n = s.length();
        int[] dp = new int[n + 1];
        
        char[] ca = s.toCharArray();
        int open = 0, max = 0;
        
        for (int i = 1; i <= n; i++) {
            if (ca[i-1] == '(') {
                open += 1;
            } else if (open > 0) {
                dp[i] = dp[i-1] + 2;
                dp[i] += i - dp[i] > 0 ? dp[i - dp[i]] : 0;
                open -= 1;
                max = Math.max(dp[i], max);
            }
        }
        
        return max;
    }

    public int longestValidParentheses_stack(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                max = Math.max(max, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        
        return max;
    }
}