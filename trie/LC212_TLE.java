class LC212_TLE {
    class Trie {
        class TrieNode {
            TrieNode[] children;
            String word;
            
            public TrieNode() {
                children = new TrieNode[26];
                word = "";
            }
        }
        
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
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
    }
    
    private int[][] dir = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };
    
    private Set<String> set = new HashSet<>();
    private Trie trie = new Trie();
    
    public List<String> findWords(char[][] board, String[] words) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (String w : words) {
            trie.insert(w);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //dfs
                dfs(board, i, j, board[i][j] + "",  visited);
            }
        }
        
        return new ArrayList<>(set);
    }
    
    private void dfs(char[][] b, int startx, int starty, String word, boolean[][] visited) {
        //这种做法会比较慢是因为每次都带着word从trie的root开始搜索
        //其实可以让trie的当前指针和dfs一起向下走，遇到一个不满足的就return
        if (trie.search(word)) {
            set.add(new String(word));
            // return; 这里不能return 比如 "aaa"和"aaab",如果return了就没有办法再找aaab
        }
        
        visited[startx][starty] = true;
        for (int[] d : dir) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= b.length || y >= b[0].length || visited[x][y]) {
                continue;
            }
            
            dfs(b, x, y, word + b[x][y], visited);
        }
        
        visited[startx][starty] = false;
    }
}