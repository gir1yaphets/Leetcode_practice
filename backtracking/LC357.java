import java.util.*;
class LC357 {
    private int res = 1; //x = 0
    
    public int countNumbersWithUniqueDigits(int n) {
        long hi = (long) Math.pow(10, n);
        boolean[] visited = new boolean[10];
        
        for (int i = 1; i <= 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(hi, i, visited);
                visited[i] = false;
            }
        }
        
        return res;
    }
    
    private void backtrack(long hi, int num, boolean[] visited) {
        if (num < hi) {
            res += 1;
        } else {
            return;
        }
        
        for (int i = 0; i <= 9; i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            
            int curr = num * 10 + i;
            
            backtrack(hi, curr, visited);
            
            visited[i] = false;
        }
    }

    /**
     * 计数方法
     */
    public int countNumbersWithUniqueDigits_Count(int n) {
        if (n == 0) {
            return 1;
        }
        
        int res = 10;
        int unique = 9;
        int availableDigits = 9;
        
        for (int i = 2; i <= n; i++) {
            unique = unique * availableDigits;
            res += unique;
            availableDigits -= 1;
        }
        
        return res;
    }
}