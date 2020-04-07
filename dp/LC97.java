import java.util.*;
class LC97 {
    private String s1, s2, s3;
    /**
     * Wrong but don't know why
     * "cabbacccabacbcaabaccacacc"
     * "bccacbacabbabaaacbbbbcbbcacc"
     * "cbccacabbacabbbaacbcacaaacbbacbcaabbbbacbcbcacccacacc"
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        
        if (s1.equals("")) return s2.equals(s3);
        if (s2.equals("")) return s1.equals(s3);
        if (s1.length() + s2.length() != s3.length()) return false;
        
        int[][] memo = new int[s1.length() + 1][s3.length() + 1];
        
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        return dfs(0, 0, "", memo) == 1;
    }
    
    private int dfs(int p1, int p3, String left, int[][] memo) {
        if (p1 == s1.length()) {
            left = left + (p3 < s3.length() ? s3.substring(p3) : "");
            memo[p1][p3] = left.equals(s2) ? 1 : 0;
            return memo[p1][p3];
        }
        
        if (p3 == s3.length()) {
            memo[p1][p3] = 0;
            return memo[p1][p3];
        }
        
        if (memo[p1][p3] != -1) return memo[p1][p3];
        
        if (s1.charAt(p1) == s3.charAt(p3)) {
            int delete = dfs(p1 + 1, p3 + 1, left, memo);
            int remain = dfs(p1, p3 + 1, left + s3.charAt(p3), memo);
            
            memo[p1][p3] = Math.max(delete, remain);
        } else {
            memo[p1][p3] = dfs(p1, p3 + 1, left + s3.charAt(p3), memo);
        }
        
        return memo[p1][p3];
    }

    /**
     * Another way of using dfs+memo
     */
    public boolean isInterleave_v2(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        
        if (s1.equals("")) return s2.equals(s3);
        if (s2.equals("")) return s1.equals(s3);
        if (s1.length() + s2.length() != s3.length()) return false;
        
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        return dfs(0, 0, 0, memo) == 1;
    }
    
    private int dfs(int p1, int p2, int p3, int[][] memo) {
        if (p3 == s3.length()) {
            memo[p1][p2] = 1;
            return 1;
        }
        
        if (p1 == s1.length() && p2 == s2.length()) {
            memo[p1][p2] = 0;
            return 0;
        }
        
        if(p1 < s1.length() && p2 < s2.length() && s1.charAt(p1) != s3.charAt(p3) && s2.charAt(p2) != s3.charAt(p3)) {
            memo[p1][p2] = 0;
            return 0;
        }
        
        if (memo[p1][p2] != -1) return memo[p1][p2];
        
        if (p1 == s1.length()) {
            if (s2.charAt(p2) != s3.charAt(p3)) return 0;
            memo[p1][p2] = dfs(p1, p2 + 1, p3 + 1, memo);
        } else if (p2 == s2.length()) {
            if (s1.charAt(p1) != s3.charAt(p3)) return 0;
            memo[p1][p2] = dfs(p1 + 1, p2, p3 + 1, memo);
        } else {
            if (s1.charAt(p1) != s3.charAt(p3)) {
                memo[p1][p2] = dfs(p1, p2 + 1, p3 + 1, memo);
            } else if (s2.charAt(p2) != s3.charAt(p3)) {
                memo[p1][p2] = dfs(p1 + 1, p2, p3 + 1, memo);
            } else {
                memo[p1][p2] = Math.max(dfs(p1, p2 + 1, p3 + 1, memo), dfs(p1 + 1, p2, p3 + 1, memo));
            }
        }
        
        return memo[p1][p2];
    }

    /**
     * bottom to up
     * 把后半部分的递归当成已知 s1.charAt(p1) == s3.charAt(p3) && dfs(p1 + 1, p2, p3 + 1, memo)
     */
    public boolean isInterleave_v3(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        
        if (s1.equals("")) return s2.equals(s3);
        if (s2.equals("")) return s1.equals(s3);
        if (s1.length() + s2.length() != s3.length()) return false;
        
        Boolean[][] memo = new Boolean[s1.length() + 1][s2.length() + 1];
        
        return dfs(0, 0, 0, memo);
    }
    
    private boolean dfs(int p1, int p2, int p3, Boolean[][] memo) {
        if (p3 == s3.length()) {
            memo[p1][p2] = true;
            return true;
        }
        
        if (memo[p1][p2] != null) return memo[p1][p2];
        
        boolean use1 = false, use2 = false;
        if (p1 < s1.length()) {
            use1 = s1.charAt(p1) == s3.charAt(p3) && dfs(p1 + 1, p2, p3 + 1, memo);
        } 
        
        if (p2 < s2.length()) {
            use2 = s2.charAt(p2) == s3.charAt(p3) && dfs(p1, p2 + 1, p3 + 1, memo);
        }
        
        memo[p1][p2] = use1 || use2;
        return memo[p1][p2];
    }
}