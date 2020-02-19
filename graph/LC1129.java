import java.util.*;

class LC1129 {
    class Pair {
        int v;
        int state; //0: red; 1: blue
        
        public Pair(int v, int s) {
            this.v = v;
            this.state = s;
        }
    }
    
    /**
     *  T: O(V + E)
     *  S: O(V + E)
     */
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[] ans = new int[n];
        boolean[] visitedRed = new boolean[n], visitedBlue = new boolean[n];
        
        Map<Integer, List<Integer>> red = new HashMap<>();
        Map<Integer, List<Integer>> blue = new HashMap<>();
        
        for (int[] e : red_edges) {
            red.putIfAbsent(e[0], new ArrayList<>());
            red.get(e[0]).add(e[1]);
        }
        
        for (int[] e : blue_edges) {
            blue.putIfAbsent(e[0], new ArrayList<>());
            blue.get(e[0]).add(e[1]);
        }
        
        //都初始化成-1，走不到的点就保留初始值-1
        Arrays.fill(ans, -1);
        
        Queue<Pair> q = new LinkedList<>();

        //红蓝同时开始一起走
        q.offer(new Pair(0, 0));
        q.offer(new Pair(0, 1));
        
        visitedRed[0] = true;
        visitedBlue[0] = true;
        
        int step = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            while (size-- > 0) {
                Pair pair = q.poll();
                int v = pair.v, state = pair.state;

                ans[v] = ans[v] >= 0 ? Math.min(ans[v], step) : step;
        
                //下一次要走的graph 和当前颜色相反
                Map<Integer, List<Integer>> g = state == 0 ? blue : red;

                //下一次要判断是否visited的点 和当前颜色相反
                boolean[] visited = state == 0 ? visitedBlue : visitedRed;
                
                if (g.get(v) == null) continue;
                
                for (int nb : g.get(v)) {
                    if (visited[nb]) continue;
                    
                    visited[nb] = true;
                    q.offer(new Pair(nb, 1 - state));
                }
            }
            
            //每层的步数+1
            step += 1;
        }
        
        return ans;
    }
}