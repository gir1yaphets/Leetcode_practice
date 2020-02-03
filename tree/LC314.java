/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    class Node {
        TreeNode node;
        int col;
        
        public Node(TreeNode n, int c) {
            this.node = n;
            this.col = c;
        }
    }
    
    /**
     * 每个节点都记住它的column index, 左节点为col - 1, 右节点为col + 1
     * 时间复杂度O(nlogn)：每个节点访问一次，并且用到treemap的insert Ologn
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Map<Integer, List<Integer>> map = new TreeMap<>();
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(root, 0));
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                Node n = q.poll();
                TreeNode node = n.node;
                int c = n.col;

                List<Integer> colList = map.getOrDefault(c, new ArrayList<>());
                colList.add(node.val);
                map.put(c, colList);
                
                if (node.left != null) {
                    q.offer(new Node(node.left, c - 1));
                }
                
                if (node.right != null) {
                    q.offer(new Node(node.right, c + 1));
                }
            }
        }
        
        for (List<Integer> list : map.values()) {
            res.add(list);
        }
        
        return res;
    }
}