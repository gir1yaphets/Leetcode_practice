import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class LC994 {
    public static void main(String[] args) {
        int[][] arr = {
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };

        int[][] arr1 = {
                {2,2,2,1,1}
        };

        new LC994().orangesRotting(arr1);
    }
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;

        int[][] dir = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        Map<Integer, Integer> dist = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    //不需要从一个点开始 只要是2的可以同时作为root加入queue
                    int root = i * m + j;
                    q.add(root);
                    dist.put(root, 0);
                }
            }
        }

        while (!q.isEmpty()) {
            // int size = q.size();
            // for (int i = 0; i < size; i++) { //只有当tree需要一行行输出的时候才需要用for循环把每一行的poll出来
                //否则不需要这个for循环，因为本来就是按照顺序加queue的
            int point = q.poll();
            int dis = dist.get(point);

            int r = point / m, c = point % m;

            for (int[] d : dir) {
                int x = r + d[0], y = c + d[1];
                int p = x * m + y;

                if (!dist.containsKey(p) && x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1) {
                    grid[x][y] = 2;

                    dist.put(p, dis + 1);
                    q.offer(p);
                    res = dist.get(p);
                }
            }
            // }
        }

        for (int[] row : grid) {
            for (int v : row) {
                if (v == 1)
                    return -1;
            }
        }

        return res;
    }
}
