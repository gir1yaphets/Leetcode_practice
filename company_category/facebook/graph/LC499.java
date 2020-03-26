package graph;
import java.util.*;
class LC499 {
    private int[][] dirs = {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0}
    };
    
    private String[] moves = {"r", "d", "l", "u"};
    
    class Point implements Comparable<Point> {
        int x;
        int y;
        int d = Integer.MAX_VALUE;
        String s = "";
            
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Point (int x, int y, int d, String path) {
            this.x = x;
            this.y = y;
            this.d = d;
            s += path;
        }
        
        public int compareTo(Point p) {
            if (this.d == p.d) {
                return s.compareTo(p.s);
            }
            
            return this.d - p.d;
        }
    }
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        
        Point[][] points = new Point[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                points[i][j] = new Point(i, j);
            }
        }
        
        PriorityQueue<Point> pq = new PriorityQueue<>();
        Point start = points[ball[0]][ball[1]];
        start.d = 0;
        pq.offer(start);
        
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            System.out.println("x = " + p.x + " y = " + p.y + " d = " + p.d);
            
            for (int i = 0; i < dirs.length; i++) {
                String move = moves[i];
                int[] dir = dirs[i];
                
                Point nextPoint = findNext(maze, p, dir, move, hole);
                
                //如果某个点的距离比之前更新的距离小，再以这个点的距离去更新其它点的距离
                if (points[nextPoint.x][nextPoint.y].compareTo(nextPoint) > 0) {
                    points[nextPoint.x][nextPoint.y] = nextPoint;
                    pq.offer(nextPoint);
                }
            }
            
        }
        
        
        return points[hole[0]][hole[1]].d == Integer.MAX_VALUE ? "impossible" : points[hole[0]][hole[1]].s;
    }
    
    /**
     * 沿着一条路径一直走到不能走 然后返回路径末尾的点
     */
    private Point findNext(int[][] maze, Point curr, int[] dir, String nextMove, int[] hole) {
        int x = curr.x, y = curr.y, d = curr.d;
        String s = curr.s + nextMove;
        boolean hitHole = false;
        
        while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
            if (x == hole[0] && y == hole[1]) {
                hitHole = true;
                break;
            }
            
            d += 1;
            x += dir[0];
            y += dir[1];
        }
        
        if (hitHole) {
            return new Point(x, y, d, s);
        }
        
        d -= 1;
        x -= dir[0];
        y -= dir[1];
        
        return new Point(x, y, d, s);
    }
}