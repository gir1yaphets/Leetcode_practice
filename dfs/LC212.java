import java.util.*;
class LC212 {
    private int[][] dir =  {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    private List<String> res = new ArrayList<>();
    
    public List<String> findWords(char[][] board, String[] words) {
        
        for (String s : words) {
            insert(s);
        }
        
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //dfs 这里不能入‘’值，直接找第一个字母
                dfs(board, i, j, board[i][j], root);
            }
        }
        
        return res;
    }
    
    public void dfs(char[][] board, int startx, int starty, char c, TrieNode t) {
        if (t.children[c - 'a'] == null) {
            return;
        }
        
        //trie的root节点是个虚节点，所以要先找到第一个包含字母的children
        t = t.children[c - 'a'];
        
        if (t.word != null && !res.contains(t.word)) {
            res.add(new String(t.word));
            t.word = null;
            // return; ***这里不能return，因为可能存在相同前缀的词，所以找到一个以后还要继续找
        }

        char letter = board[startx][starty];
        board[startx][starty] = '#';
        
        for (int[] d : dir) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == '#') {
                continue;
            }
            
            dfs(board, x, y, board[x][y], t);
        }
        
        board[startx][starty] = letter;
        
    }
    
    class TrieNode{
        String word;
        TrieNode[] children;
        
        public TrieNode() {
            word = null;
            children = new TrieNode[26];
        }
    }
    
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode curr = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }

            curr = curr.children[c - 'a'];
        }

        curr.word = word;
    }
}