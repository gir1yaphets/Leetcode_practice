import java.util.*;

class LC131 {
    public static void main(String[] args) {
        String s = "aab";
        LC131 lc131 = new LC131();
        List<List<String>> res = lc131.partition(s);
        for (List<String> item : res) {
            for (String p : item) {
                System.out.println(p);
            }

            System.out.println("\n");
        }
    }
    public List<List<String>> partition(String s) {
        List<String> item = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        
        dfs(s, 0, item, res);
        return res;
    }
    
    private void dfs(String s, int start, List<String> item, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(item));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            //先从起点开始找到一个palindrome，然后以该点为起点继续做dfs，需要进行backtracking找到所有可能性
            if (isPalindrome(s, start, i)) {
                item.add(s.substring(start, i + 1));
                dfs(s, i + 1, item, res);
                item.remove(item.size() - 1);
            }
        }
    }
    
    /**
     * 从两边开始向中间确认
     * @param s
     * @param left
     * @param right
     * @return
     */
    private boolean isPalindrome(String s, int left, int right) {
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