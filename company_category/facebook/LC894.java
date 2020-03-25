import java.util.*;

class LC894 {
    private Map<Integer, List<TreeNode>> cache = new HashMap<>();
    
    public List<TreeNode> allPossibleFBT(int N) {
        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        
        List<TreeNode> res = new ArrayList<>();
        
        if (N % 2 == 0) {
            return res;
        }
        
        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        
        //以为N只能是偶数才有可能构成full binary tree, 所以N从1开始m i += 2
        for (int i = 1; i < N; i += 2) {
            //枚举所有左子树和右子树
            for (TreeNode leftTree : allPossibleFBT(i)) {
                //左子树i个，根节点1个，所以右子树N-i-1
                for (TreeNode rightTree : allPossibleFBT(N - i - 1)) {
                    TreeNode root = new TreeNode(0);
                    root.left = leftTree;
                    root.right = rightTree;
                    
                    res.add(root);
                }
            }
        }
        
        cache.put(N, res);
        return res;
    }
}