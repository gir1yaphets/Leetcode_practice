class Solution {
    private String temp = "";
    
    public String smallestStringWithSwaps_dfs(String s, List<List<Integer>> pairs) {
        char[] ca = s.toCharArray();
        Map<Integer, List<Integer>> g = new HashMap<>();
        boolean[] visited = new boolean[s.length()];
        
        for (List<Integer> edge : pairs) {
            int u = edge.get(0), v = edge.get(1);
            List<Integer> ulist = g.getOrDefault(u, new ArrayList<>());
            List<Integer> vlist = g.getOrDefault(v, new ArrayList<>());
            
            ulist.add(v);
            vlist.add(u);
            
            g.put(u, ulist);
            g.put(v, vlist);
        }

        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < ca.length; i++) {
            if (visited[i]) continue;
            index.clear();
            temp = "";
            
            dfs(ca, i, g, index, visited);
            
            char[] tempArray = temp.toCharArray();
            Arrays.sort(tempArray);
            Collections.sort(index);
            
            for (int j = 0; j < index.size(); j++) {
                ca[index.get(j)] = tempArray[j];
            }
        }
        
        return new String(ca);
    }
    
    private void dfs(char[] ca, int v, Map<Integer, List<Integer>> g, List<Integer> index, boolean[] visited) {
        temp += ca[v];
        index.add(v);

        visited[v] = true;
        List<Integer> nblist = g.get(v);
        
        if (nblist != null) {
            for (int nb : nblist) {
                if (!visited[nb]) {
                    dfs(ca, nb, g, index, visited);
                }
            }
        }
    }



    /**
     * Union Find
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] ca = s.toCharArray();
        int n = ca.length;
        
        int[] root = new int[n];
        
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        
        for (List<Integer> edge : pairs) {
            int u = edge.get(0), v = edge.get(1);
            
            int rootu = find(root, u);
            int rootv = find(root, v);
            
            if (rootu != rootv) {
                root[rootv] = rootu;
            }
        }
        
        //将同一个root下面的char放到priorityqueue中进行按字母顺序排列
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int rooti = find(root, i);
            map.putIfAbsent(rooti, new PriorityQueue<>());
            map.get(rooti).offer(ca[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(map.get(find(root, i)).poll());
        }
        
        return sb.toString();
    }
    
    private int find(int[] root, int x) {
        if (root[x] != x) {
            root[x] = find(root, root[x]);
        }
        
        return root[x];
    }
}