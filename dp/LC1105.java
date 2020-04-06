class LC1105 {
    public static void main(String[] args) {
        // int[][] books = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
        int[][] books = {{7,3},{8,7},{2,7},{2,5}};
        new LC1105().minHeightShelves(books, 10);
    }
    private int min = Integer.MAX_VALUE;
    
    public int minHeightShelves(int[][] books, int shelf_width) {
        dfs(books, 0, shelf_width, 0, 0, 0);
        return min;
    }
    
    /**
     * TLE
     * @param books
     * @param start
     * @param shelf_width
     * @param w：当前层的宽度
     * @param h：当前总高度
     * @param levelHeight:每一层的最大高度
     */
    private void dfs(int[][] books, int start, int shelf_width, int w, int h, int levelHeight) {
        if (h > min) return;
        if (w > shelf_width) return;

        if (start == books.length) {
            min = Math.min(min, h + levelHeight);
            return;
        }
        
        dfs(books, start + 1, shelf_width, w + books[start][0], h, Math.max(levelHeight, books[start][1]));
        
        dfs(books, start + 1, shelf_width, books[start][0], h + levelHeight, books[start][1]);
    }

    /**
     * memo recursion
     * @param books
     * @param shelf_width
     * @return
     */
    public int minHeightShelves_memo(int[][] books, int shelf_width) {
        int[][] memo = new int[books.length + 1][shelf_width + 1];
        return dfs(books, 0, shelf_width, shelf_width, 0, memo);
    }
    
    /**
     * 
     * @param books
     * @param start
     * @param shelf_width
     * @param wr: width remain
     * @param h: 当前层的高度
     * @param memo
     * @return
     */
    private int dfs(int[][] books, int start, int shelf_width, int wr, int h, int[][] memo) {
        if (start == books.length) return h;
        
        if (memo[start][wr] != 0) return memo[start][wr];
        
        int height = Integer.MAX_VALUE;
        
        //当前层还能放下
        if (wr >= books[start][0]) {
            height = dfs(books, start + 1, shelf_width, wr - books[start][0], Math.max(h, books[start][1]), memo);
        }
        
        //min(放在当前层的高度，放在下一层的高度)
        height = Math.min(height, h + dfs(books, start + 1, shelf_width, shelf_width - books[start][0], books[start][1], memo));
        
        memo[start][wr] = height;
        return height;
    }

    /**
     * dp[i]:摆放0~i本书需要的最小高度
     */
    public int minHeightShelves_dp(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            int w = 0, h = 0;
            for (int j = i; j >= 0; j--) {
                if ((w += books[j][0]) > shelf_width) break;
                
                //把j~i放在一层 dp(0~j-1)已经计算过
                h = Math.max(h, books[j][1]);
                
                dp[i] = Math.min(dp[i], (j == 0 ? h : dp[j-1] + h));
            }
        }
        
        return dp[n-1];
    }
}