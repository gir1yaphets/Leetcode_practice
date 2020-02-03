import java.util.*;

class LC140 {
    public static void main(String[] args) {
        new LC140().wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
    }

    private List<String> res = new ArrayList<>();
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        dfs(s, 0, wordDict, new String());
        return res;
    }
    
    private void dfs(String s, int start, List<String> dict, String sol) {
        if (start == s.length()) {
            res.add(new String(sol).trim());
            return;
        }
        
        for (int end = start + 1; end <= s.length(); end++) {
            if (!dict.contains(s.substring(start, end))) {
                continue;
            }

            //这种回溯有问题 cat, cats 当回溯到cat的时候 遇到了s变成cats 这时候cat没有办法被删除
            sol += s.substring(start, end) + " ";
            dfs(s, end, dict, sol);
        }
    }

    /**
     * 正确 but TLE
     */
    // private List<String> res = new ArrayList<>();
    
    public List<String> wordBreak_tle(String s, List<String> wordDict) {
        dfs(s, 0, wordDict, new ArrayList<>());
        return res;
    }
    
    private void dfs(String s, int start, List<String> dict, List<String> sol) {
        if (start == s.length()) {
            res.add(convert(new ArrayList<>(sol)));
            return;
        }
        
        for (int end = start + 1; end <= s.length(); end++) {
            if (!dict.contains(s.substring(start, end))) {
                continue;
            }

            // sol += s.substring(start, end) + " ";
            sol.add(s.substring(start, end));
            dfs(s, end, dict, sol);
            sol.remove(sol.size() - 1);
        }
    }
    
    private String convert(List<String> sol) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sol.size(); i++) {
            sb.append(sol.get(i));
            if (i < sol.size() - 1) {
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }
}