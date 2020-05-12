import java.util.*;

class LC1306 {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(start);
        
        while (!q.isEmpty()) {
            int index = q.poll();
            visited.add(index);
            
            if (arr[index] == 0) return true;
            
            int plus = index + arr[index], minus = index - arr[index];
            
            if (plus < n && !visited.contains(plus)) {
                q.offer(plus);
            }
            
            if (minus >= 0 && !visited.contains(minus)) {
                q.offer(minus);
            }
        }
        
        return false;
    }

    private boolean found = false;
    public boolean canReach_dfs(int[] arr, int start) {
        dfs(arr, start, new HashSet<>());
        return found;
    }
    
    private void dfs(int[] arr, int index, Set<Integer> visited) {
        visited.add(index);
        
        if (arr[index] == 0) found = true;
            
        int plus = index + arr[index], minus = index - arr[index];

        if (plus < arr.length && !visited.contains(plus)) {
            dfs(arr, plus, visited);
        }

        if (minus >= 0 && !visited.contains(minus)) {
            dfs(arr, minus, visited);
        }
    }
}