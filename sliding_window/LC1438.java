import java.util.*;

public class LC1438 {
    /**
     * sliding window + 单调队列(维护当前范围内的最大值和最小值)
     * 求当前范围内最小值，维护一个单调递增队列，每次遇到比队列尾小的元素就将队列尾poll掉，保证最小值在队头
     * 当前范围内最大值，维护一个单调递减队列，每次遇到比队列尾大的元素就将队列尾poll掉，保证最大值在队头
     */
    public int longestSubarray(int[] nums, int limit) {
        int len = 0;
        int l = 0, r = 0;
        Deque<Integer> maxQueue = new ArrayDeque<>(), minQueue = new ArrayDeque<>();
        
        while (r < nums.length) {
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < nums[r]) {
                maxQueue.pollLast();
            }
            maxQueue.offerLast(nums[r]);
            
            while (!minQueue.isEmpty() && minQueue.peekLast() > nums[r]) {
                minQueue.pollLast();
            }
            minQueue.offerLast(nums[r]);
            
            r += 1;

            while (maxQueue.peekFirst() - minQueue.peekFirst() > limit) {
                if (nums[l] == minQueue.peekFirst()) {
                    minQueue.pollFirst();
                } else if (nums[l] == maxQueue.peekFirst()) {
                    maxQueue.pollFirst();
                }
                
                l += 1;
            }
            
            len = Math.max(len, r - l);
        }
        
        return len;
    }
}