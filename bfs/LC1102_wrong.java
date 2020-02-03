import java.util.*;

class LC1102_wrong {
    private int[][] dir = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public static void main(String[] args) {
        int[][] grid = {
            {5,4,5},
            {1,2,6},
            {7,4,6}
        };

        new LC1102_wrong().maximumMinimumPath(grid);
    }
    
    public int maximumMinimumPath(int[][] A) {
        int m = A.length, n = A[0].length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[m * n];
        
        q.offer(0);
        pq.add(A[0][0]);
        visited[0] = true;
        int max = A[0][0];
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int pos = q.poll();
                int r = pos / n, c = pos % n;

                for (int[] d : dir) {
                    int nr = r + d[0], nc = c + d[1];
                    int npos = nr * n + nc;
                    if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[npos]) {
                        continue;
                    }
                    
                    if (nr == m - 1 && nc == n - 1) {
                        return Math.max(A[nr][nc], pq.peek());
                    }

                    max = Math.max(max, A[nr][nc]);
                    
                    q.add(npos);
                    visited[npos] = true;
                }
            }
            
            //把每层最大的值加入到pq，但是这个最大值不一定能走到
            //而且这样会忽略掉下一层的最小值，比如第一层最大值是5，第二层最大值是1，但是max取的是5，所以1就没有办法加进来
            pq.add(max);
        }
        
        return pq.peek();
    }
}