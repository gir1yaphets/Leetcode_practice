import java.util.*;
class LC784 {
    private List<String> res = new ArrayList<>();
    
    public List<String> letterCasePermutation_mine(String S) {
        res.add(S);
        dfs(S.toCharArray(), 0);
        return res;
    }
    
    private void dfs(char[] ca, int start) {
        for (int i = start; i < ca.length; i++) {
            if (Character.isDigit(ca[i])) {
                continue;
            }
            
            boolean isLower = Character.isLowerCase(ca[i]);
            
            if (isLower) {
                ca[i] = Character.toUpperCase(ca[i]);
            } else {
                ca[i] = Character.toLowerCase(ca[i]);
            }
            
            //每次只改变一位，加如到解集合中
            res.add(new String(ca));
            
            dfs(ca, i + 1);
            
            if (isLower) {
                ca[i] = Character.toLowerCase(ca[i]);
            } else {
                ca[i] = Character.toUpperCase(ca[i]);
            }
        }
    }


    /**
     * 这种dfs是每次对一个节点的一个字母分为大写小写然后构建二叉树，二叉树的叶子节点就是所有的解集合
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        dfs_2(S.toCharArray(), 0);
        return res;
    }
    
    private void dfs_2(char[] ca, int level) {
        if (level == ca.length) {
            res.add(new String(ca));
            return;
        }
        
        if (Character.isDigit(ca[level])) {
            dfs(ca, level + 1);
        } else {
            ca[level] = Character.toLowerCase(ca[level]);
            dfs(ca, level + 1);

            ca[level] = Character.toUpperCase(ca[level]);
            dfs(ca, level + 1);
        }
    }
}