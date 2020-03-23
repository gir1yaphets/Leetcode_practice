/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC776 {
    public TreeNode[] splitBST(TreeNode root, int V) {
        if (root == null) {
            //[SmallorEqual, Large]
            return new TreeNode[]{null, null};
        }
        
        //目标值小于当前节点的值，切割左子树
        if (V < root.val) {
            TreeNode[] res = splitBST(root.left, V);
            //root是大的一边，所以需要连接上被切割的大的一边的子树，而大的一边的子树就存在res[1]中
            root.left = res[1];
            //因为当前root是大的一遍 所以赋值给res[1]
            res[1] = root;
            return res;
        } else { //目标值大于等于当前节点的值，切割右子树
            TreeNode[] res = splitBST(root.right, V);
            //因为要将小于等于的放到res[0]，所以else表示的是root.val <= V
            root.right = res[0];
            res[0] = root;
            return res;
        }
    }
}