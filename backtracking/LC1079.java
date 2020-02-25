class LC1079 {
    private int count = 0;
    
    public int numTilePossibilities(String tiles) {
        if (tiles == null) return 0;
        
        char[] ca = tiles.toCharArray();
        //将相同字母排序到一起
        Arrays.sort(ca);
        
        backtrack(ca, "", new boolean[ca.length]);
        
        return count;
    }
    
    private void backtrack(char[] ca, String curr, boolean[] visited) {
        for (int i = 0; i < ca.length; i++) {
            if (visited[i]) continue;
            
            //同Leetcode 47解释
            if (i > 0 && ca[i] == ca[i-1] && !visited[i-1]) continue;

            visited[i] = true;
            count += 1;
            
            backtrack(ca, curr + ca[i], visited);
            
            visited[i] = false;
        }
    }
}