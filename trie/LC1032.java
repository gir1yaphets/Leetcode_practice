class LC1032 {
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        
        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    private TrieNode root;
    private StringBuilder sb;
    
    public StreamChecker(String[] words) {
        root = new TrieNode();
        sb = new StringBuilder();
        
        for (String w : words) {
            TrieNode node = root;
            char[] ca = w.toCharArray();
            
            for (int i = ca.length - 1; i >= 0; i--) {
                int p = ca[i] - 'a';
                if (node.children[p] == null) {
                    node.children[p] = new TrieNode();
                }
                
                node = node.children[p];
            }
            
            node.isWord = true;
        }        
    }
    
    public boolean query(char letter) {
        sb.append(letter);
        TrieNode node = root;
        
        for (int i = sb.length() - 1; i >= 0 && node != null; i--) {
            char c = sb.charAt(i);
            /**
             * 错误写法先check null，
             * ["StreamChecker","query","query","query","query","query","query","query","query","query","query","query","query"]
             * [[["cd","f","kl"]],["a"],["b"],["c"],["d"],["e"],["f"],["g"],["h"],["i"],["j"],["k"],["l"]]
             * 当e进来的时候sb是"edcba"" 如果先check null的话e没有就继续找d，d又找到了，所以错误
             * 每次check一个字母都需要移动一次node
             */
            
            // if (node.children[c - 'a'] != null) {
                node = node.children[c - 'a'];
                    
                if (node != null && node.isWord) {
                    return true;
                }
            // }
        }
        
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */