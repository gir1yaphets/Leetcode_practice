import java.util.*;
class LC1414 {
    private Set<Integer> fibSet = new HashSet<>();
    
    public int findMinFibonacciNumbers_TLE(int k) {
        if (k == 1 || k == 2) return 1;
        
        getFib(k);
        return dfs(k);
    }
    
    private int dfs(int num) {
        if (num == 0) return 0;
        
        int n = num;
        while (!fibSet.contains(num)) {
            num -= 1;
        }
        
        return 1 + dfs(n - num);
    }
    
    private void getFib(int n) {
        int pre1 = 1, pre2 = 1;
        fibSet.add(1);
        
        for (int i = 2; i <= n; i++) {
            int curr = pre1 + pre2;
            if (curr > n) {
                break;
            }
            
            fibSet.add(curr);
            pre2 = pre1;
            pre1 = curr;
        }
    }

    public int findMinFibonacciNumbers(int k) {
        if (k < 2) return k;
        int a = 1, b = 1;
        //找小于等于当前k的最大fib数
        while (b <= k) {
            b += a;
            a = b - a;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
}