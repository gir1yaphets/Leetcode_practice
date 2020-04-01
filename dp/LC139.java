import java.util.Arrays;
import java.util.*;
class LC139 {
    public static void main(String[] args) {
        new LC139().wordBreak_memo("leetcode", Arrays.asList("leet", "code"));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        
        dp[0] = true;
        
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[len];
    }

    public boolean wordBreak_prune(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        
        int maxLen = 0;
        
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        
        dp[0] = true;
        
        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= Math.max(0, i - maxLen); j--) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[len];
    }

    /**
     * 和dp的方向正好相反 先检查左边字符串是否包含，再递归检查右边是否能被拆分，如果可以记录当前在memo[start]这一点可以拆分
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak_memo(String s, List<String> wordDict) {
        Boolean[] memo = new Boolean[s.length()];
        return helper(s, wordDict, 0, memo);
    }

    private boolean helper(String s, List<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        
        if (memo[start] != null) {
            return memo[start];
        }
        
        for (int pos = start + 1; pos <= s.length(); pos++) {
            //check left side whether contained in dict
            if (!wordDict.contains(s.substring(start, pos))) {
                continue;
            }
            
            
            //check right side is able to be segmented recursively
            if (helper(s, wordDict, pos, memo)) {
                //可以在start这一点分割
                memo[start] = true;
                return true;
            }
        }
        
        memo[start] = false;
        return false;
    }

    /**
     * memo 第二种写法 和第一种基本类似
     */
    public boolean wordBreak_memo2(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) return false;
        int[] memo = new int[s.length()];
        
        return dfs(0, s, wordDict, memo);
    }
    
    private boolean dfs(int start, String s, List<String> dict, int[] memo) {
        if (start == s.length()) return true;
        
        if (memo[start] != 0) return memo[start] == 1;
        
        for (int i = start; i < s.length(); i++) {
            String first = s.substring(start, i + 1);
            
            if (dict.contains(first) && dfs(i + 1, s, dict, memo)) {
                memo[start] = 1;
                return true;
            }
        }
        
        memo[start] = -1;
        
        return false;
    }
}