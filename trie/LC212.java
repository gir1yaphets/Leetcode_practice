class Solution {
    class TrieNode {
        TrieNode[] children;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
            word = "";
        }
    }

    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }

            node = node.children[c - 'a'];
        }

        node.word = word;
    }

    public boolean search(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (node.children[c - 'a'] == null) return false;

            node = node.children[c - 'a'];
        }


        boolean res = false;
        if (node.word.equals(word)) {
            res = true;
            node.word = "";
        }
        return res;
    }
    
    private int[][] dir = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };
    
    private List<String> res = new ArrayList<>();
    private TrieNode root = new TrieNode();
    
    public List<String> findWords(char[][] board, String[] words) {
        for (String w : words) {
            insert(w);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //dfs
                dfs(board, i, j, board[i][j], root);
            }
        }
        
        return res;
    }
    
    private void dfs(char[][] b, int startx, int starty, char l, TrieNode node) {
        if (node.children[l - 'a'] == null) {
            return;
        }
        
        node = node.children[l - 'a'];
        
        if (!"".equals(node.word)) {
            res.add(new String(node.word));
            node.word = "";
        }

        char letter = b[startx][starty];
        b[startx][starty] = '#';
        
        for (int[] d : dir) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= b.length || y >= b[0].length || b[x][y] == '#') {
                continue;
            }
            
            dfs(b, x, y, b[x][y], node);
        }
        
        b[startx][starty] = letter;
    }
}