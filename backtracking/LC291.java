import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
class LC291 {
    public boolean wordPatternMatch(String pattern, String str) {
        return backtrack(str, pattern, 0, 0, new HashMap<>(), new HashSet<>());
    }
    
    private boolean backtrack(String str, String pattern, int indexS, int indexP, Map<Character, String> map, Set<String> set) {
        if (indexS == str.length() && indexP == pattern.length()) return true;
        
        if (indexS == str.length() || indexP == pattern.length()) return false;
        
        char c = pattern.charAt(indexP);
        //pattern中的当前字符之前已经匹配了一个string，该再次出现判断是否和之前出现的一样
        if (map.containsKey(c)) {
            String mapping = map.get(c);
            
            if (!str.startsWith(mapping, indexS)) {
                return false;
            }
            
            return backtrack(str, pattern, indexS + mapping.length(), indexP + 1, map, set);
        }
        
        //pattern中没出现过该字符，开始一个个尝试
        for (int i = indexS; i < str.length(); i++) {
            String trying = str.substring(indexS, i + 1);
            
            //为了避免a->red,b->red的情况，如果red已经map给了a，则不让b不再map到red
            if (set.contains(trying)) {
                continue;
            }
            
            set.add(trying);
            map.put(c, trying);
            
            if (backtrack(str, pattern, i + 1, indexP + 1, map, set)) {
                return true;
            }
            
            set.remove(trying);
            map.remove(c);
        }
        
        return false;
    }
}