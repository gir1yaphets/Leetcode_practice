class SnakeGame {

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    
    private int w;
    private int h;
    private int len;
    private int[] pos;
    private int[][] food;
    private int fi = 0;
    private Deque<int[]> path;
    private Set<Integer> set = new HashSet<>();
    
    public SnakeGame(int width, int height, int[][] food) {
        w = width;
        h = height;
        len = 0;
        
        this.food = food;
        pos = new int[] {0, 0};
        path = new ArrayDeque<>();
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int r = 0, c = 0;
        switch (direction) {
            case "U":
                c = pos[1]; 
                r = pos[0] - 1;
                if (r < 0 || set.contains(r * w + c)) {
                    return -1;
                }
                break;
            case "D":
                c = pos[1]; 
                r = pos[0] + 1;
                if (r >= h || set.contains(r * w + c)) {
                    return -1;
                }
                
                break;
            case "L":
                c = pos[1] - 1;
                r = pos[0];
                if (c < 0 || set.contains(r * w + c)) {
                    return -1;
                }
                
                break;
            case "R":
                c = pos[1] + 1; 
                r = pos[0];
                if (c >= w || set.contains(r * w + c)) {
                    return -1;
                }
                
                break;
            default:
                break;
        }
        
        getFood(r, c);
        updatePath(r, c);
        
        return len;
    }
    
    private void updatePath(int r, int c) {
        pos[0] = r;
        pos[1] = c;
        set.add(r * w + c);
        path.addFirst(new int[]{r, c});
        if (path.size() > len) {
            int[] last = path.pollLast();
            set.remove(last[0] * w + last[1]);
        }
    }
    
    private void getFood(int r, int c) {
        if (fi < food.length && c == food[fi][1] && r == food[fi][0]) {
            fi += 1;
            len += 1;
            
        }
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */