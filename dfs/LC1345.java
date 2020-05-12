import java.util.*;
class LC1345 {
    public int minJumps_bfs(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        int step = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int index = q.poll();
                if (index == arr.length - 1) return step;
                
                visited.add(index);
                
                List<Integer> next = map.get(arr[index]);
                if (index + 1 < arr.length) next.add(index + 1);
                if (index - 1 >= 0) next.add(index - 1);
                
                for (int ni : next) {
                    if (!visited.contains(ni)) {
                        q.offer(ni);
                    }
                }
                //避免相同值的index被反复检查e.g [7,7,7,7,7,7,7,7,8]
                //第一次会把index 0的7加入队列，然后把所有7都加入队列
                //如果不左clear的话，第二次对于每一个7仍然需要左visited的检查，这个clear会把map中key=7的list清空
                next.clear();
            }
            
            step += 1;
        }
        
        return 0;
    }

    /**
     * ----------------------------------
     * DFS wrong
     */
    private Map<Integer, List<Integer>> map;
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private Set<Integer> visited = new HashSet<>();
    
    public static void main(String[] args) {
        int[] arr = {100,-23,-23,404,100,23,23,23,3,404};
        new LC1345().minJumps(arr);
    }

    public int minJumps(int[] arr) {
        int n = arr.length;
        map = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        buildGraph(arr);
        return dfs(arr, 0);
    }
    
    private int dfs(int[] arr, int index) {
        if (index == arr.length - 1) return 0;
        visited.add(index);
        int count = arr.length;
        
        if (g.get(index) != null) {
            for (int nb : g.get(index)) {
                if (!visited.contains(nb)) {
                    count = Math.min(count, 1 + dfs(arr, nb));
                }
            }
        }
        
        return count;
    }
    
    private void buildGraph(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            g.putIfAbsent(i, new HashSet<>());
            if (i + 1 < arr.length) {
                g.get(i).add(i + 1);
            }
            
            if (i - 1 >= 0) {
                g.get(i).add(i - 1);
            }
            
            for (int index : map.get(arr[i])) {
                if (index != i) {
                    g.get(i).add(index);
                }
            }
        }
    }
    
}