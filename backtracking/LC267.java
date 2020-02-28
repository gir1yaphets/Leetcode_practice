import java.util.ArrayList;
import java.util.List;

class LC267 {
    private List<String> sol = new ArrayList<>();
        
    public List<String> generatePalindromes(String s) {
        char[] ca = s.toCharArray();
        int[] map = new int[256];
        
        for (char c : ca) {
            map[c] += 1;
        }
        
        StringBuilder sb = new StringBuilder();
        
        //将单数的字符直接append到stringbuider上 因为单数的字符只能出现一次 不存在排列的情况
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 == 1) {
                sb.append((char) i);
            }
        }
        
        if (sb.length() > 1) {
            return sol;
        }
        
        backtrack(map, sb, ca.length);
        
        return sol;
    }
    
    private void backtrack(int[] map, StringBuilder curr, int len) {
        if (curr.length() == len) {
            sol.add(curr.toString());
            return;
        }
        
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 1) {
                map[i] -= 2;
                
                curr.insert(0, (char) i);
                curr.append((char) i);
                
                backtrack(map, curr, len);
                
                curr.deleteCharAt(0);
                curr.deleteCharAt(curr.length() - 1);
                
                map[i] += 2;
            }
        }
    }
}