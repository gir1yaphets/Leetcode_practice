
import java.util.*;

class LC523 {
    public static void main(String[] args) {
        int[] nums = {2, 0};
        int k = 2;
        new LC523().checkSubarraySum(nums, k);
    }


    /**
     * 如果[0, j]的和是rem [0, i]的和还是rem 并且i-j>1 那么[j,i]的和就是n * k
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, -1);
        
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            
            if (k != 0) {
                sum %= k;
            }
            
            if (preSum.containsKey(sum)) {
                if (i - preSum.get(sum) > 1) {
                    return true;
                }
            } else {
                preSum.put(sum, i);
            }

            //Wrong!!! [2, 0] k = 0
            //preSum.put(sum, i);
        }
        
        return false;
    }
}