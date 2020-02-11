import java.util.*;

class LC1102 {
    private int[][] dir = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    class Cell {
        int r;
        int c;
        int val;
        
        public Cell(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.val = v;
        }
    }
    public int maximumMinimumPath(int[][] A) {
        int m = A.length, n = A[0].length;
        
        //维护一个大顶堆，每次从堆顶拿最大cell的值
        //有一种情况比如走[1, 4, 2, 1], [3,3,3,3]
        //第一次在3和4之间选择4，但是沿着4走会走到2和1，不满足要求，这个时候大顶堆会把第二行的3 poll出来沿着3继续走
        //所以并不会向贪心算法一样每次只选择最大的值，但是最终结果不满足要求
        PriorityQueue<Cell> pq = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);
        boolean[] visited = new boolean[m * n];
        
        pq.add(new Cell(0, 0, A[0][0]));
        visited[0] = true;
        int min = Math.min(A[0][0], A[m-1][n-1]);
        
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            int r = cell.r, c = cell.c, val = cell.val;

            //因为poll出来的都是当前最大的点，所以直接更新min
            min = Math.min(min, val);

            for (int[] d : dir) {
                int nr = r + d[0], nc = c + d[1];
                int npos = nr * n + nc;
                if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[npos]) {
                    continue;
                }

                if (nr == m - 1 && nc == n - 1) {
                    return min;
                }

                visited[npos] = true;
                pq.add(new Cell(nr, nc, A[nr][nc]));
            }
        }
        
        return min;
    }
}