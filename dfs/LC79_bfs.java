import java.util.*;
class LC79_bfs {
    private static int[][] dir = {
        {1, 0},
        {-1, 0},
        {0, -1},
        {0, 1}
    };

    public static void main(String[] args) {
        char[][] board = {
            {'C', 'A', 'A'},
            {'A', 'A', 'A'},
            {'B', 'C', 'D'}
        };

        exist(board, "AAB");
    }
    
    public static boolean exist(char[][] board, String word) {
        if (board.length == 0) return false;
        
        int m = board.length, n = board[0].length;
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> dist = new HashMap<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //dfs
                if (board[i][j] != word.charAt(0)) {
                    continue;
                }
                
                int pos = i * n + j;
                q.offer(pos);
                dist.put(pos, 1);
                
                while (!q.isEmpty()) {
                    int p = q.poll();

                    int r = p / n, c = p % n;
                    int level = dist.get(p);

                    // System.out.println(level);

                    if (level == word.length()) {
                        return true;
                    }

                    for (int[] d : dir) {
                        int x = r + d[0], y = c + d[1];
                        int nextPos = x * n + y;
                        // System.out.println("x = " + x + " y = " + y);
                        if (x < 0 || y < 0 || x >= m || y >= n || board[x][y] != word.charAt(level) || dist.containsKey(nextPos)) {
                            continue;
                        }

                        System.out.println(nextPos);

                        q.offer(nextPos);
                        dist.put(nextPos, level + 1);

                    }
                }
            }
        }
        
        
        return false;
    }
}