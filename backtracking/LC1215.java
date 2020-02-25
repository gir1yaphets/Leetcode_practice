import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class LC1215 {
    private int hi = 0, lo = 0;
    
    public List<Integer> countSteppingNumbers(int low, int high) {
        hi = high;
        lo = low;
        
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            backtrack(i, res);
        }
        
        Collections.sort(res);
        
        return res;
    }
    
    private void backtrack(long num, List<Integer> sol) {
        if (num >= lo && num <= hi) {
            sol.add((int) num);
        }
        
        if (num == 0 || num > hi) return;
        
        int lastDigit = (int) num % 10;
        long up = num * 10 + lastDigit + 1;
        long down = num * 10 + lastDigit - 1;
        
        if (lastDigit != 0) {
            backtrack(down, sol);
        } 
        
        if (lastDigit != 9) {
            backtrack(up, sol);
        } 
    }
}