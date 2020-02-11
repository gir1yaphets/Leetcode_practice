import java.util.*;

class LC269 {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> g = new HashMap<>();
        int[] indegree = new int[26];
        buildGraph(words, g, indegree);
        return bfs(g, indegree);
    }
    
    private void buildGraph(String[] words, Map<Character, Set<Character>> g, int[] indegree) {
        //将所有出现过的字母作为graph的一个顶点
        for (String word : words) {
            for (char c : word.toCharArray()) {
                g.putIfAbsent(c, new HashSet<>());
            }
        }
        
        for (int i = 1; i < words.length; i++) {
            //相邻两个字符串进行字母比较
            String first = words[i - 1];
            String second = words[i];
            
            int minLen = Math.min(first.length(), second.length());
            
            for (int j = 0; j < minLen; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);
                if (out != in) {
                    //Map的key是set 不会重复添加 但是indegree不能加多次
                    if (!g.get(out).contains(in)) {
                        g.get(out).add(in);
                        indegree[in - 'a']++;
                    }
                    //只要发现一个不同的就确定了一个关系 后面的则不能再比较了 因为第一个的不同的字符决定了顺序
                    break;
                }
            }
        }
    }
    
    private String bfs(Map<Character, Set<Character>> g, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        
        for (char c : g.keySet()) {
            if (indegree[c - 'a'] == 0) {
                q.offer(c);
            }
        }
        
        while (!q.isEmpty()) {
            char out = q.poll();
            sb.append(out);
            
            if (g.get(out) != null && g.get(out).size() > 0) {
                for (char in : g.get(out)) {
                    if (--indegree[in - 'a'] == 0) {
                        q.offer(in);
                    }
                }
            }
        }
        
        return sb.length() == g.size() ? sb.toString() : "";
    }
}