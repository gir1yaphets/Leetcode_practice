import java.util.*;

/**
 * TBD Wrong solution...
 */
class LC1215 {
    private int hi = 0;
    
    public List<Integer> countSteppingNumbers(int low, int high) {
        int[] array = new int[10];
        boolean[] visited = new boolean[10];
        
        hi = high;
        
        List<Integer> res = new ArrayList<>();
        
        backtrack(array, 0, visited, low, res);
        return res;
    }
    
    private void backtrack(int[] array, int start, boolean[] visited, int num, List<Integer> sol) {
        sol.add(num);
        
        if (start == array.length) return;
            
        for (int i = 0; i < array.length; i++) {
            if (visited[i]) continue;
            
            if (Math.abs(array[start] - array[i]) > 1) continue;

            visited[i] = true;
            num = num * 10 + array[i];
            if (num > hi) continue;
            backtrack(array, i + 1, visited, num, sol);
            visited[i] = false;
        }
    }
}