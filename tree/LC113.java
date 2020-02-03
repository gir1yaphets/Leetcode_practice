/**
1.注意树中可能存在负数，所以不能判断当加上下一个节点大于目标的时候就剪枝
2.回溯条件
  1.当前叶子结点已经满足条件，删除重新走下一个子树
  2.当前节点左右子树已经走完，将该节点删除
*/
class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return res;

        helper(root, 0, sum, new ArrayList<>());
        return res;
    }

    private void helper(TreeNode root, int sum, int target, List<Integer> sol) {
        if (root == null) return;

        sum += root.val;
        sol.add(root.val);

        if (root.left == null && root.right == null) {
            if (sum == target) {
                res.add(new ArrayList<>(sol));
                //当走到满足sum的叶子节点时，在这个if里面就回溯了，remove掉了自己，所以就不会再调用helper(left), helper(right)都返回null之后再remove，所以需要在这里进行回溯
                sol.remove(sol.size() - 1);
                return;
            }
        }

        helper(root.left, sum, target, sol);
        helper(root.right, sum, target, sol);

        sol.remove(sol.size() - 1);
    }
}