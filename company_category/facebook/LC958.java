import java.util.*;
class LC958 {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        
        //For a complete binary tree, there should not be any node after we met an empty one.
        boolean hasEmptyNode = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                
                if (node != null) {
                    if (!hasEmptyNode) {
                        q.offer(node.left);
                        q.offer(node.right);
                    } else {
                        //之前有过空节点，则一定不是完全二叉树
                        return false;
                    }
                } else {
                    //第一次遇到空节点的时候记录flag
                    hasEmptyNode = true;
                }
            }
        }
        
        return true;
    }
}