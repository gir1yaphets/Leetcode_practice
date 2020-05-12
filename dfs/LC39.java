import java.util.*;
class LC39 {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0, 0, new ArrayList<>());
        return res;
    }
    
    /**
     * 需要用start记录当前遍历的其实位置，否则会出现解的排列组合
     * [2,3,6,7] 当搜索到3的时候避免再回去搜索2，因为2的所有解都已经搜索完了
     * @param can
     * @param target
     * @param sum
     * @param start
     * @param sol
     */
    private void dfs(int[] can, int target, int sum, int start, List<Integer> sol) {
        if (sum == target) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = start; i < can.length; i++) {
            //放在这里而不是方法开头，避免一次递归
            if (sum + can[i] > target) {
                continue;
            }
            
            sol.add(can[i]);
            dfs(can, target, sum + can[i], i, sol);
            sol.remove(sol.size() - 1);
        }
    }
}