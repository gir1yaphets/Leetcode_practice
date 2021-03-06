import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class LC429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            List<Integer> line = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                line.add(node.val);
                
                if (node.children != null) {
                    for (Node child : node.children) {
                        q.offer(child);
                    }
                }
            }
            
            res.add(line);
        }
        
        return res;
    }
}