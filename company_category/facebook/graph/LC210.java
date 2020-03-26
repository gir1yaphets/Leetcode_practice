package graph;

import java.util.*;
class LC210 {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    private List<Integer> list = new ArrayList<>();
    
    public int[] findOrder(int numCourses, int[][] pre) {
        int[] visited = new int[numCourses];
        
        buildGraph(pre);
        
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (!dfs(i, visited)) {
                    return new int[0];
                }
            }
        }
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
    
    private void buildGraph(int[][] pre) {
        for (int[] e : pre) {
            g.putIfAbsent(e[1], new ArrayList<>());
            g.get(e[1]).add(e[0]);
        }
    }
    
    private boolean dfs(int start, int[] visited) {
        if (visited[start] == 1) {
            return false;
        }
        
        if (visited[start] == 2) {
            return true;
        }
        
        visited[start] = 1;
        
        if (g.get(start) != null) {
            for (int nb : g.get(start)) {
                if (!dfs(nb, visited)) {
                    return false;
                }
            }
        }
        
        visited[start] = 2;
        list.add(0, start);
        
        return true;
    }
}