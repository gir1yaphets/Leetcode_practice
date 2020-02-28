import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class LC1291 {
    private List<Integer> res = new ArrayList<>();
    
    public List<Integer> sequentialDigits(int low, int high) {
        for (int i = 0; i < 9; i++) {
            backtrack(low, high, i);
        }
        
        Collections.sort(res);
        
        return res;
    }
    
    private void backtrack(int lo, int hi, int curr) {
        if (curr >= lo && curr <= hi) {
            res.add(curr);
        }
        
        if (curr > hi || curr == 0) {
            return;
        }
        
        int last = curr % 10;
        if (last + 1 < 10) {
            int up = curr * 10 + last + 1;
            backtrack(lo, hi, up);
        }
    }
}