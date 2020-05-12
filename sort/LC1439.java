import java.util.*;
class LC1439 {
    /**
     * 第k小的数，如果用pq，最后答案要在堆顶，所以要用max heap
     */
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        
        for (int j = 0; j < n; j++) {
            pq.offer(mat[0][j]);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        for (int i = 1; i < m; i++) {
            PriorityQueue<Integer> nextPq = new PriorityQueue<>(Comparator.reverseOrder());
            while (!pq.isEmpty()) {
                int num = pq.poll();
                for (int j = 0; j < n; j++) {
                    nextPq.offer(num + mat[i][j]);
                    
                    if (nextPq.size() > k) {
                        nextPq.poll();
                    }
                }
            }
            
            pq = nextPq;
        }
        
        return pq.poll();
    }


    /**
     * 4ms
     * time: m * k * log5000m
     * binaary search的时间复杂度是log5000m
     * backtracking的时间复杂度是m * min(n^i, k) 而k比较小 所以就是m * k
     */
    public int kthSmallest_binarysearch(int[][] mat, int k) {
        int m = mat.length;
        int l = m, r = 5000 * m;
        
        while (l < r) {
            int mid = l + (r - l) / 2;
            
            int count = countNumLessOrEqualTarget(mat, 0, k, 0, mid);
            if (count < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        
        return l;
    }
    
    private int countNumLessOrEqualTarget(int[][] mat, int r, int k, int sum, int target) {
        if (sum > target) return 0;
        if (r == mat.length) return 1;
        
        int ans = 0;
        for (int i = 0; i < mat[0].length; i++) {
            int count = countNumLessOrEqualTarget(mat, r + 1, k - ans, sum + mat[r][i], target);
            if (count == 0) {
                break;
            }
            ans += count;
            
            //如果当前这种选法得到得答案比k个数大的话，说明target选小了，继续用binary search找下一个target
            if (ans > k) {
                break;
            }
        }
        
        return ans;
    }
}