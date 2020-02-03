class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms.size() == 0) return false;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        
        while (!q.isEmpty()) {
            int index = q.poll();
            
            List<Integer> keys = rooms.get(index);
            
            for (int k : keys) {
                if (!visited.contains(k)) {
                    q.offer(k);
                    visited.add(k);
                }
            }
        }
        
        return visited.size() == rooms.size();
    }
}