import java.util.*;

class LC1258 {
    private Map<String, List<String>> g = new HashMap<>();
    private String[] words;
    private List<String> res = new ArrayList<>();
    
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        buildGraph(synonyms);
        
        words = text.split(" ");
        
        dfs(0, "");
        Collections.sort(res);
        return res;
    }
    
    private void buildGraph(List<List<String>> sy) {
        for (List<String> item : sy) {
            String u = item.get(0), v = item.get(1);
            g.putIfAbsent(u, new ArrayList<>());
            g.putIfAbsent(v, new ArrayList<>());
            
            g.get(u).add(v);
            g.get(v).add(u);
        }
    }
    
    private void dfs(int index, String curr) {
        if (index == words.length) {
            res.add(curr);
            return;
        }
        String word = words[index];

        if (g.containsKey(word) && g.get(word) != null) {
            List<String> chain = new ArrayList<>();
            dfsGraph(word, new HashSet<>(), chain);
            
            for (String s : chain) {
                dfs(index + 1, curr + (index == 0 ? s : " " + s));
            }
        } else {
            curr += (index == 0 ? word : " " + word);
            dfs(index + 1, curr);
        }
    }
    
    private void dfsGraph(String word, Set<String> visited, List<String> chain) {
        if (visited.contains(word)) return;
        visited.add(word);
        chain.add(word);
            
        for (String nb : g.get(word)) {
            dfsGraph(nb, visited, chain);
        }
    }
}