import java.util.*;
class LC1048 {
    public int longestStrChain(String[] words) {
        int n = words.length;
        if (n == 0) return 0;
        
        int[] dp = new int[n];
        int max = 0;
        
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            
            for (int j = 0; j < i; j++) {
                if (isPre(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
    
    private boolean isPre(String a, String b) {
        if (b.length() - a.length() != 1) {
            return false;
        }

        char[] cha = a.toCharArray();
        char[] chb = b.toCharArray();
        int idx1 = 0, idx2 = 0;
        int diff = 0;
        
        while (idx1 < cha.length) {
            if (cha[idx1] != chb[idx2]) {
                idx2 += 1;
                diff += 1;
            } else {
                idx1 += 1;
                idx2 += 1;
            }
            
            if (diff == 2) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Map dp
     */
    public int longestStrChain_mapDp(String[] words) {
        int n = words.length;
        if (n == 0) return 0;
        
        Map<String, Integer> dp = new HashMap<>();
        int max = 0;
        
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        
        for (String word : words) {
            if (dp.containsKey(word)) continue;
            dp.put(word, 1);
            
            int num = 0;
            for (int i = 0; i < word.length(); i++) {
                String pre = word.substring(0, i) + word.substring(i+1);
                num = Math.max(num, dp.getOrDefault(pre, 0) + 1);
            }
            
            dp.put(word, num);
            
            max = Math.max(max, dp.get(word));
        }
        
        return max;
    }
}