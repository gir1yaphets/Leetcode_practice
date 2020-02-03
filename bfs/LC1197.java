import java.util.*;

class LC1197 {
    public static void main(String[] args) {
        minKnightMoves(2, 1);
    }

    private static int[][] dir = {
        {2, 1},
        {-2, 1},
        {1, 2},
        {-1, 2},
        {2, -1},
        {-2, -1},
        {1, -2},
        {-1, -2}
    };
    
    static class Point {
        public int x;
        public int y;
        public int d;
        
        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    
    public static int minKnightMoves(int x, int y) {
        Set<String> visited = new HashSet<>();
        Queue<Point> q = new LinkedList<>();
        Point init = new Point(0, 0, 0);
        q.offer(init);
        visited.add(0 + "" + 0);
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            int dist = p.d + 1;
            
            for (int[] d : dir) {
                int r = p.x + d[0], c = p.y + d[1];

                if (x == r && y == c) {
                    return dist;
                }
                
                if (visited.contains(r + "" + c)) {
                    continue;
                }
                
                q.offer(new Point(r, c, dist));
            }
        }
        
        return -1;
    }
}