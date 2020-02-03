import java.util.*;
class LC17 {
    private static String[] map = {
        "",
        "",
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    };

    
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        
        if (digits == null || digits.length() == 0) return res;
        
        //dfs遍历digits中的每一位，然后将其对应的字母填到答案中
        dfs(digits, "", 0, res);
        return res;
    }
    
    private void dfs(String digits, String curr, int start, List<String> res) {
        if (start == digits.length()) {
            res.add(curr);
            return;
        }
        
        int digit = digits.charAt(start) - '0';
        if (digit < 2 || digit > 9) {
            return;
        }
        
        String letters = map[digit];
        char[] ca = letters.toCharArray();
        
        for (int i = 0; i < ca.length; i++) {
            //从字母中找一位填到curr中然后dfs下一位数字
            dfs(digits, curr + ca[i], start + 1, res);
        }
    }
}