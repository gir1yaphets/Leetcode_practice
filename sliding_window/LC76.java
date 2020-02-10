class LC76 {
    public static void main(String[] args) {
        new LC76().minWindow("aa", "aa");
    }

    /**
     * 1. 先移动右指针让该窗口包含所有t中的字符串，每找到一个右边的字符，count--
     * 2. 当count == 0时说明t中的所有字符都找到了，开始移动左指针直到从窗口中移除掉一个t中的字符，然后再继续移动右指针寻找新的窗口
     * 3. 更新minL和minR的值找到最小窗口
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        int[] map_t = new int[256];
        
        int l = 0, r = 0;
        int minL = 0, minR = Integer.MAX_VALUE;
        
        int count = t.length();
        
        for (char c : t.toCharArray()) {
            map_t[c]++;
        }
        
        while (r < s.length()) {
            char rs = s.charAt(r++);

            //先移动右指针，如果该字符存在于t中，则count--
            if (--map_t[rs] >= 0) {
                count--;
            }
            
            while (count == 0) {
                char ls = s.charAt(l);
                if (++map_t[ls] > 0) {
                    count += 1;
                }
                
                if (minR - minL > r - l) {
                    minL = l;
                    minR = r;
                }
                
                l++;
            }
        }
        
        return minR == Integer.MAX_VALUE ? "" : s.substring(minL, minR);
    }
}