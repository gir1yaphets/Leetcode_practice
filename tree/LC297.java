/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    public static void main(String[] args) {
        new Codec().deserialize("1,2,null,null,3,4,null,null,5,null,null");
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> nodes = new ArrayList<>();
        helper(root, nodes);
        
        String res = "";
        for (int i = 0; i < nodes.size(); i++) {
            res += nodes.get(i);
            if (i != nodes.size() - 1) {
                res += ",";
            }
        }
        
        System.out.println(res);
        return res;
    }
    
    private void helper(TreeNode root, List<String> res) {
        if (root == null) {
            res.add("null");
            return;
        }
        
        res.add(String.valueOf(root.val));
        
        helper(root.left, res);
        helper(root.right, res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        
        return helper2(nodes, 0);
    }
    
    private TreeNode helper2(String[] data, int index) {
        if (index == data.length || data[index].equals("null")) {
            return null;
        }
        
        System.out.println(Integer.valueOf(data[index]));
        TreeNode node = new TreeNode(Integer.valueOf(data[index]));
        node.left = helper2(data, index + 1);
        node.right = helper2(data, index + 2);
        
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));