class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int[] ca = new int[256];
        
        int l = 0, r = 0;
        int max = 0;
        
        while (r < s.length()) {
            //每次先移动右指针，并将有指针走过的内容记录下来
            char cr = s.charAt(r++);
            ca[cr] += 1;
            
            //移动右指针以后判断是否满足题目的条件，比如出现重复的，总长度大于几个了，并调整左指针保证当前窗口内的内容满足条件
            while (ca[cr] > 1) {
                char cl = s.charAt(l);
                ca[cl]--;
                l++;
            }
            
            //统计结果，注意当题目条件为满足长度为某个数的时候，这里仍然需要加判断长度，否则会将小于该长度的值加进来
            max = Math.max(max, r - l);
        }
        
        return max;
    }
}