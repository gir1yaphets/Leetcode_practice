import java.util.*;
class Solution {
    public static void main(String[] args) {
        int[][] graph = {
            {1, 3},
            {0, 2},
            {1, 3},
            {0, 2}
        };
        new Solution().isBipartite(graph);
    }
    public boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length];
        
        //需要判断每一个节点，不能只dfs开始节点，否则如果不是所有都连通 则有节点可能访问不到
        for (int i = 0; i < graph.length; i++) {
            //如果当前节点没有进行过dfs，则对该节点进行dfs 如果dfs结果为false 则直接返回false
            if (visited[i] == 0 && !dfs(graph, visited, i, 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 
     * @param graph
     * @param visited
     * @param start
     * @param color: 当前节点应该被标记的颜色
     * @return
     */
    private boolean dfs(int[][] graph, int[] visited, int start, int color) {
        //如果当前节点已经被标记过，则判断一下是否和目标标记颜色一样，不一样返回false
        if (visited[start] != 0) {
            return visited[start] == color;
        }

        visited[start] = color;
        
        for (int v : graph[start]) {
            if (!dfs(graph, visited, v, color * -1)) {
                return false;
            }
        }
        
        return true;
    }
}