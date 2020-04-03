import java.util.*;
/**
 * "102213"
 *  e.g当解到第二个1的时候，这一位的解码方式有[1022, 1]和[102, 21]
 *  所以1这一位解码方式就是1022的解码方式 + 102的解码方式
 */
class LC91 {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int n = s.length();
        int[] dp = new int[n + 1];
        
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        
        for (int i = 2; i <= n; i++) {
            int oneDitigNum = Integer.valueOf(s.substring(i-1, i));
            int twoDigitsNum = Integer.valueOf(s.substring(i-2, i));
            
            if (oneDitigNum != 0) {
                dp[i] += dp[i-1];
            }
            
            if (twoDigitsNum >= 10 && twoDigitsNum <= 26) {
                dp[i] += dp[i-2];
            }
        }
        
        return dp[n];
    }

    public int numDecodings_memo_dfs(String s) {
        if (s == null || s.length() == 0) return 0;
        
        return dfs(s, 0, new HashMap<>());
    }
    
    private int dfs(String s, int index, Map<Integer, Integer> memo) {
        if (index == s.length()) return 1;
        
        if (index == s.length() - 1) return 1;
        
        if (s.charAt(index) == '0') return 0;
        
        if (memo.containsKey(index)) return memo.get(index);

        int sum = 0;
        
        //parse one digit
        int oneDigitNum = s.charAt(index) - '0';
        
        if (oneDigitNum != 0) {
            sum += dfs(s, index + 1, memo);
        }
        
        //parse two digits
        int twoDigitsNum = (s.charAt(index) - '0') * 10 + (s.charAt(index + 1) - '0');
        
        if (twoDigitsNum >= 10 && twoDigitsNum <= 26) {
            sum += dfs(s, index + 2, memo);
        }

        memo.put(index, sum);
        
        return sum;
    }
}