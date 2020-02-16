import java.util.*;

class LC294 {
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) return false;
        
        return canWin(s, new HashMap<>());
    }
    
    private boolean canWin(String s, Map<String, Boolean> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
                
                if (!canWin(opponent, memo)) {
                    memo.put(s, true);
                    return true;
                }
            }
        }
        
        memo.put(s, false);
        return false;
    }
}