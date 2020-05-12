class LC1358 {
    /**
     * sliding window中是刚好不满足包含abc的窗口
     * 那么这时候左指针左边有几个字母，就可以构成多少个substring是包含abc的
     * e.g abcabc
     * 第一次满足包含abc的时候左边有0个，这时候移动左指针1次并将第一个a移除窗口
     * 然后右指针继续移动，又进来一个a，这个时候左指针需要移动个1个使得窗口刚好不满足条件
     * 那么这时候左指针l = 2,说明它左侧又两个字母，这样加上窗口中不满足的就能构成两个substring使得包含abc
     * 即abca,bca
     * 正确性：因为窗口里面是*刚好*不满足条件的串，所以左边有几个字母就能构成几个substring使得满足条件
     */
    public int numberOfSubstrings(String s) {
        int[] count = new int[3];
        int ans = 0, index = 0;
        char[] ca = s.toCharArray();
        
        for (int i = 0; i < s.length(); i++) {
            count[ca[i] - 'a'] += 1;
            while (containsAll(count)) {
                count[ca[index++] - 'a'] -= 1;
            }
            ans += index;
        }

        return ans;
    }
    
    
    private boolean containsAll(int[] count) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] <= 0) {
                return false;
            }
        }
        
        return true;
    }
}