import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LC1239 {
    private int max = 0;
    
    public int maxLength(List<String> arr) {
        Set<Character> counter = new HashSet<>();
        List<String> unique = new ArrayList<>();
        
        for (int i = 0; i < arr.size(); i++) {
            counter.clear();
            String s = arr.get(i);
            
            boolean isUnique = true;
            for (char c : s.toCharArray()) {
                if (counter.contains(c)) {
                    isUnique = false;
                    break;
                }
                counter.add(c);
            }
            
            if (isUnique) {
                unique.add(s);
            }
        }
        
        backtrack(unique, 0, new HashSet<>());
        return max;
    }
    
    private void backtrack(List<String> arr, int start, Set<Character> set) {
        if (start == arr.size()) {
            return;
        }
        
        for (int i = start; i < arr.size(); i++) {
            String s = arr.get(i);
            
            if (check(s, set)) {
                char[] ca = s.toCharArray();
                for (char c : ca) {
                    set.add(c);
                }
                max = Math.max(max, set.size());
                backtrack(arr, i + 1, set);
                for (char c : ca) {
                    set.remove(c);
                }
            }
        }
    }
    
    private boolean check(String s, Set<Character> set) {
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
        }
        
        return true;
    }
}