import java.util.*;

public class LC801 {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] swap = new int[n], keep = new int[n];
        
        Arrays.fill(swap, Integer.MAX_VALUE);
        Arrays.fill(keep, Integer.MAX_VALUE);
        
        swap[0] = 1;
        keep[0] = 0;
        
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i-1] && B[i] > B[i-1]) {
                swap[i] = swap[i-1] + 1;
                keep[i] = keep[i-1];
            }
            
            if (A[i] > B[i-1] && B[i] > A[i-1]) {
                swap[i] = Math.min(swap[i], keep[i-1] + 1);
                keep[i] = Math.min(keep[i], swap[i-1]);
            }
        }
        
        return Math.min(swap[n-1], keep[n-1]);
    }
}