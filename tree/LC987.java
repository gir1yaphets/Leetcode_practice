import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC987 {
    class NodeCol {
        TreeNode node;
        int col;
        int row;
        
        public NodeCol(TreeNode n, int c, int r) {
            this.node = n;
            this.col = c;
            this.row = r;
        }
    }
    
    private int minCol = 0, maxCol = 0;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<List<NodeCol>> nodeList = new ArrayList<>();
        
        if (root == null) return res;
        
        Queue<NodeCol> q = new LinkedList<>();
        q.offer(new NodeCol(root, 0, 0));
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            NodeCol nc = q.poll();
            TreeNode node = nc.node;
            int col = nc.col;
            int row = nc.row;

            List<NodeCol> colList;
            if (col < minCol) {
                colList = new ArrayList<>();
                nodeList.add(0, colList);
                minCol = col;
            } else if (col > maxCol) {
                colList = new ArrayList<>();
                nodeList.add(colList);
                maxCol = col;
            } else {
                if (nodeList.size() == 0) {
                    colList = new ArrayList<>();
                    nodeList.add(colList);
                } else {
                    colList = nodeList.get(col - minCol);
                }
            }

            colList.add(nc);

            if (node.left != null) {
                q.offer(new NodeCol(node.left, col - 1, row + 1));
            }

            if (node.right != null) {
                q.offer(new NodeCol(node.right, col + 1, row + 1));
            }
        }
        
        for (List<NodeCol> list : nodeList) {
            List<Integer> item = new ArrayList<>();
            //如果有相同行的两个元素，按照大小排序
            Collections.sort(list, (o1, o2) -> {
                if (o1.row == o2.row) {
                    return o1.node.val - o2.node.val;
                }
                
                return 0;
            });
            
            for (NodeCol nc : list) {
                item.add(nc.node.val);
            }
            res.add(item);
        }
        
        return res;
    }
}