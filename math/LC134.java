class LC134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length) return -1;
        int n = gas.length;
        int start = 0;
        int total = 0, currSum = 0;
        
        for (int i = 0; i < n; i++) {
            //记录当前到当前的gas和cost差的和 当差值总和小于0的时候说明以该点为起点不能套圈 说明解应该在i的右边 将start更新为i+1
            currSum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            
            if (currSum < 0) {
                currSum = 0;
                start = i + 1;
            }
        }
        
        return total < 0 ? -1 : start;
    }
}