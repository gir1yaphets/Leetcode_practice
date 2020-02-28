import java.util.ArrayList;
import java.util.List;

class LC842 {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        backtrack(S, 0, res);
        return res;
    }
    
    private boolean backtrack(String s, int start, List<Integer> sol) {
        if (start == s.length() && sol.size() >= 3) return true;
        
        for (int i = start; i < s.length(); i++) {
            if (i > start && s.charAt(start) == '0') {
                continue;
            }
            
            int num = 0;
            
            try {
                num = Integer.parseInt(s.substring(start, i + 1));
            } catch (Exception e) {
                break;
            }
            
            int size = sol.size();
            
            if (size >= 2 && num > sol.get(size - 1) + sol.get(size - 2)) {
                return false;
            }
            
            if (size < 2 || num == sol.get(size - 1) + sol.get(size - 2)) {
                sol.add((int) num);
                if (backtrack(s, i + 1, sol)) {
                    return true;
                }
                
                sol.remove(sol.size() - 1);
            }
        }
        
        return false;
    }
}