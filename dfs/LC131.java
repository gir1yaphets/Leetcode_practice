import java.util.*;

class LC131 {
    private List<List<String>> res = new ArrayList<>();
    public List<List<String>> partition(String s) {
        //dfs
        dfs(s, 0, new ArrayList<>());
        return res;
    }
    
    private void dfs(String s, int start, List<String> sol) {
        if (start == s.length()) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            //从起始位置开始向后找 当找到一个回文串以后将其加入到sol中，然后再以下一个位置作为起始位置继续向后找
            //当起始位置走到最后的时候即可以将s分为多个回文串
            if (isPalindrome(s, start, i)) {
                sol.add(s.substring(start, i + 1));
                dfs(s, i + 1, sol);
                sol.remove(sol.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int left, int right) {
        //这里要从两边向中间检查
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
}