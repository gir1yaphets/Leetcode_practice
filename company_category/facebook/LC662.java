import java.util.*;

class Solution {
    class Node {
        TreeNode treeNode;
        int pos;
        int depth;
        
        public Node(TreeNode n, int p, int d) {
            treeNode = n;
            pos = p;
            depth = d;
        }
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(root, 0, 0));
        int left = 0, currDepth = 0;
        int res = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                int pos = node.pos;
                int depth = node.depth;

                if (node.treeNode.left != null) {
                    q.offer(new Node(node.treeNode.left, 2 * pos, depth + 1));
                }

                if (node.treeNode.right != null) {
                    q.offer(new Node(node.treeNode.right, 2 * pos + 1, depth + 1));
                }
                
                //第一次更新depth的节点一定是最左边的节点
                if (depth > currDepth) {
                    currDepth = depth;
                    left = pos;
                }
                
                //最后一个节点是最右边的节点
                res = Math.max(res, pos - left + 1);
            }
        }
        
        return res;
    }
}