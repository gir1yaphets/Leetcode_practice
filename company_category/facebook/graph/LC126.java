package graph;
import java.util.*;

/**
 * 1. 先用bfs从begin开始到end过程中所有经过的word标记距离，记录在dist中
 * 2. 用dfs从从begin开始，每次找dist中距离当前word为1的neighbor进行搜索，当到达end的时候记录一条有效路径，然后在backtrack
 */
class LC126 {
    private Map<String, Integer> dist = new HashMap<>();
    private Map<String, List<String>> g = new HashMap<>();
    private List<List<String>> res = new ArrayList<>();
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return res;
        
        Set<String> dict = new HashSet<>(wordList);
        dict.add(beginWord);

        /** 提前将dict中所有的word都进行查找neighbors,然后将其加入到g中
         *  这样会导致一些无法从begin到达的word也被加入到了g中，所以在dfs过程中还需要判断dist.get(nb) != null,因为所有的word不一定在dist的map中
            g.put(beginWord, getNeighbors(beginWord, dict));
            
            for (String w : wordList) {
                g.put(w, getNeighbors(w, dict));
            }
        */

        if (bfs(beginWord, endWord, dict)) {
            dfs(beginWord, endWord, new ArrayList<>());
        }
         
        
        return res;
    }
    
    private boolean bfs(String begin, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        dist.put(begin, 0);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                
                if (curr.equals(end)) return true;
                
                int d = dist.get(curr);

                //只将在bfs过程中可以达到的词进行查找neighbor并且加入到g中，其它词不需要管
                List<String> neighbors = getNeighbors(curr, dict);
                g.get(curr).addAll(neighbors);
                
                for (String nb : g.get(curr)) {
                    if (!dist.containsKey(nb)) {
                        q.offer(nb);
                        dist.put(nb, d + 1);
                    }
                }
            }
        }
        
        return false;
    }
    
    private void dfs(String start, String end, List<String> path) {
        path.add(start);
        
        if (start.equals(end)) {
            res.add(new ArrayList<>(path));
        } else {
            for (String nb : g.get(start)) {
                //只加如可以到到达的词就不需要判断 dist.get(nb) != null
                if (dist.get(nb) == dist.get(start) + 1) {
                    dfs(nb, end, path);
                }
            }
        }
        
        path.remove(path.size() - 1);
    }
    
    private List<String> getNeighbors(String s, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        char[] ca = s.toCharArray();
        
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < ca.length; i++) {
                if (ch == ca[i]) continue;
                
                char org = ca[i];
                ca[i] = ch;
                
                if (dict.contains(String.valueOf(ca))) {
                    neighbors.add(String.valueOf(ca));
                }
                
                ca[i] = org;
            }
        }
        
        return neighbors;
    }
}