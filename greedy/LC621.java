import java.util.*;
class LC621 {
    public int leastInterval(char[] tasks, int n) {
        //先按照字母个数进行排序 个数最多的在最后
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        
        Arrays.sort(map);

        //找到个数最多的字母 然后减1 为了找到需要多少个空档填cool down 最后一段是不需要空档的
        int maxVal = map[25] - 1;
        //先假设这些空档全填上idle
        int idleSlots = maxVal * n;
        
        //从个数第二大的开始填这些空位置 用之前假设的idle个数减去能够填的字母个数
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            //因为maxVal是出现最多的次数-1, 如果存在和其个数一样的字母个数，应该取小的
            //比如A=3 B=3 n=2 那么如果先把A固定, 最后的B放到最后的A后面就可以 并不消耗idle
            idleSlots -= Math.min(map[i], maxVal); 
        }
        
        //最后的结果就是剩余没被字母填满的idle个数 + 整个task的长度
        return idleSlots > 0 ? tasks.length + idleSlots : tasks.length;
    }
}