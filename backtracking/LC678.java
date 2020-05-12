
public class LC678 {
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) return true;
        
        char[] ca = s.toCharArray();
        return dfs(ca, 0, 0, 0);
    }
    
    private boolean dfs(char[] ca, int index, int l, int r) {
        if (index == ca.length) return l == r;
        
        if (ca[index] == '(') {
            return dfs(ca, index + 1, l + 1, r);
        } else if (ca[index] == ')') {
            if (r >= l) {
                return false;
            }
            return dfs(ca, index + 1, l, r + 1);
        } else {
            boolean asLeft = dfs(ca, index + 1, l + 1, r);
            boolean asRight = dfs(ca, index + 1, l, r + 1);
            boolean asEmpty = dfs(ca, index + 1, l, r);
            return asLeft || asRight || asEmpty;
        }
    }

    /**
     * 记录一个count表示未匹配左括号的数量
     * 如果遇到左括号 则[lo, hi]++
     * 如果遇到右括号 则[lo, hi]--
     * 如果遇到* [lo--, hi++]，因为如果*表示左括号，则未匹配的数量就增加了一个，如果*表示右括号，则未匹配的数量就减少1
     * 最后判断是否存在可能使得lo==0,说明存在一种方案使得左右括号完全匹配
     */
    public boolean checkValidString_ON(String s) {
        if (s == null || s.length() == 0) return true;
        char[] ca = s.toCharArray();
        
        int lo = 0, hi = 0;
        
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] == '(') {
                lo += 1; 
                hi += 1;
            } else if (ca[i] == ')') {
                lo = Math.max(0, lo - 1);
                hi -= 1;
            } else {
                lo = Math.max(0, lo - 1);
                hi += 1;
            }
            if (hi < 0) return false;
        }
        
        return lo == 0;
    }
}