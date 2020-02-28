import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LC187 {
    public List<String> findRepeatedDnaSequences(String s) {
        int l = 0, r = 0;
        Set<String> res = new HashSet<>();
        Set<String> set = new HashSet<>();
        
        while (r < s.length()) {
            while (r - l < 9 && r < s.length()) {
                r++;
            }
            
            if (r - l < 9 || r == s.length()) break;
            
            String sub = s.substring(l, r + 1);
            if (set.contains(sub)) {
                res.add(sub);
            } else {
                set.add(sub);
            }
            
            r++;
            l++;
        }
        
        return new ArrayList<>(res);
    }
}