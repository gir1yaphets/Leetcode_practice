/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class Solution {
    public Node cloneGraph(Node node) {
        return dfs(node, new HashMap<>());
    }
    
    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        
        if (map.containsKey(node)) {
            return map.get(node);
        }
        
        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);
        
        for (Node nb : node.neighbors) {
            clone.neighbors.add(dfs(nb, map));
        }
        
        return clone;
    }

    public Node cloneGraph_bfs(Node node) {
        if (node == null) return null;
        
        Queue<Node> q = new LinkedList<>();
        Map<Node, Node> map = new HashMap<>();
        
        Node clone = new Node(node.val, new ArrayList<>());
        q.offer(node);
        map.put(node, clone);
        
        while (!q.isEmpty()) {
            Node n = q.poll();
            Node c = map.get(n);
            
            for (Node nb : n.neighbors) {
                Node cloneNb;
                if (!map.containsKey(nb)) {
                    cloneNb = new Node(nb.val, new ArrayList<>());
                    map.put(nb, cloneNb);
                    q.offer(nb);
                }
                c.neighbors.add(map.get(nb));
            }
        }
        
        return clone;
    }
}