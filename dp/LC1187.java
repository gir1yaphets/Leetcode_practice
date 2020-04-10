import java.util.*;
class LC1187 {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        int k = 1;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[i] != arr2[i-1]) arr2[k++] = arr2[i];
        }
        
        int[][] memo = new int[arr1.length + 1][arr2.length + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        int op = dfs(arr1, arr2, 0, 0, -1, memo);
        
        return op >= arr2.length ? -1 : op;
    }
    
    private int dfs(int[] arr1, int[] arr2, int p1, int p2, int prev, int[][] memo) {
        if (p1 == arr1.length) {
            return 0;
        }
        
        if (memo[p1][p2] != Integer.MAX_VALUE) return memo[p1][p2];
        
        int min = arr2.length;
        if (arr1[p1] > prev) {
            min = dfs(arr1, arr2, p1 + 1, p2, arr1[p1], memo);
        } 
        
        while (p2 < arr2.length && arr2[p2] <= prev) p2++;

        if (p2 < arr2.length) {
            min = Math.min(1 + dfs(arr1, arr2, p1 + 1, p2 + 1, arr2[p2], memo), min);
        }
        
        memo[p1][p2] = min;
        return min;
    }
}