import java.util.*;
class LC254 {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> getFactors(int n) {
        dfs(n, 2, new ArrayList<>());
        return res;
    }
    
    private void dfs(int n, int start, List<Integer> sol) {
        if (n <= 1) {
            if (sol.size() > 1) {
                res.add(new ArrayList<>(sol));
            }
            return;
        }
        
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                sol.add(i);
                dfs(n / i, i, sol);
                sol.remove(sol.size() - 1);
            }
        }
    }
}