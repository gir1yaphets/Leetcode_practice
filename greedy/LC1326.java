public class LC1326 {
    public static void main(String[] args) {
        // int[] ranges = {1,2,1,0,2,1,0,1};
        int[] ranges = {0,0,0,0};
        new LC1326().minTaps(3, ranges);
    }

    public int minTaps(int n, int[] ranges) {
        int[] nums = new int[n+1];
        
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            //更新从左边起能达到的最远的位置，nums[left]如果被覆盖，只可能被更大的值覆盖，因为i在增加，如果左边能到达left，说明右边i+range[i]也比之前的更远
            nums[left] = i + ranges[i];
        }
        
        //LC45 Jump Game II
        int currEnd = 0, farest = 0, steps = 0;
        for (int i = 0; i <= n; i++) {
            //正常情况下，如果可以继续向后跳，那么end会被更新，如果当前i已经达到了之前更新的end，说明这个end已经无法继续更新了
            if (i > farest) return -1;
            //当i超过起点的时候，直接更新最远值作为当前终点
            //因为当前终点也要被计算最远距离，所以需要等i>currEnd的时候再更更新currEnd
            if (i > currEnd) {
                currEnd = farest;
                steps += 1;
            }
            
            //在达到终点之前以及在终点位置的时候，不断更新下一次能走到的最远距离
            farest = Math.max(farest, nums[i]);
        }
        
        return steps;
    }

    /**
     * nums = {3,3,6,3,6,6,7,7}
     * start = 0, end = 0 i = 0 end = nums[0] = 3
     * i = 1 i > start start = 3 end = 3
     * i = 2 i < start end = nums[2] = 6
     * i = 3 i == start end = 6
     * i = 4 i > start start = 6 end = 6
     * i = 5 i < start end = 6
     * i = 6 i = start end = nums[6] = 7
     * i =  7 i > start start = 7
     */
}