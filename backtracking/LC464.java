class LC464 {
    public static void main(String[] args) {
        new LC464().canIWin(3, 4);
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        
        if (desiredTotal <= 0) return true;
        
        byte[] visited = new byte[1 << maxChoosableInteger];
        
        //判断当前player是否能赢，第一个player先调用，所以返回的就是第一个player的结果
        return canWin(maxChoosableInteger, desiredTotal, visited, 0);
    }
    
    /**
     * @param maxChoosableInteger
     * @param remain
     * @param visited: current all solutions, seems like a HashMap<Integer, Boolean> key is all current used number, value is the result for that solution
     *                 [0]: unknown; [-1]: lose; [1]: win
     * @param state: bitmask for all the number used, to check whether current number is used
     * @return
     */
    private boolean canWin(int maxChoosableInteger, int remain, byte[] visited, int state) {
        if (visited[state] != 0) return visited[state] == 1;
        
        for (int i = 0; i < maxChoosableInteger; i++) {
            if ((state & (1 << i)) != 0) continue;
            
            //I can use (i+1) to win or opponent cannot use remaining (i+1) to win -> I will win
            //But if canWin return true for the opponent, it will continuously check other solution
            //***Only when whatever opponent choose, current player will always win, return current player wins.
            if (remain - (i + 1) <= 0 || !canWin(maxChoosableInteger, remain - (i + 1), visited, state | (1 << i))) {
                visited[state] = 1;
                return true;
            }
        }
        
        //if all the combination for current player cannot win, then update the result
        visited[state] = -1;
        
        return false;
    }
}
    