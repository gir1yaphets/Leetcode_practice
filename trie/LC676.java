import java.util.*;
class LC676 {
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        
        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }
    
    private TrieNode root;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String w : dict) {
            TrieNode node = root;
            char[] ca = w.toCharArray();
            
            for (int i = 0; i < ca.length; i++) {
                int index = ca[i] - 'a';
                
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                
                node = node.children[index];
            }
            
            node.isWord = true;
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return dfs(word.toCharArray(), 0, root, 0);
    }
    
    private boolean dfs(char[] ca, int index, TrieNode node, int diff) {
        if (index == ca.length) {
            return node.isWord && diff == 1;
        }
        
        int ci = ca[index] - 'a';
        
        for (int i = 0; i < 26; i++) {
            if (node.children[i] == null) continue;
            
            //如果选择和当前word中的字母相同，则diff不变，否则diff + 1
            if (dfs(ca, index + 1, node.children[i], ci == i ? diff : diff + 1)) {
                return true;
            }
        }
        
        return false;
    }
}