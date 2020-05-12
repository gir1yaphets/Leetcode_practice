import java.util.*;
class LC1405 {
    public static void main(String[] args) {
        new LC1405().longestDiverseString(0, 8, 11);
    }

    /**
     * 错误的做法是如果某一个字母剩余数量还>2就先放两个进来，如果<2就放一个
     * 这样不能保证最后的结果是最长的
     * 0 8 11
     * wrong:   "ccbbccbbccbbccbbcc"
     * correct: "ccbccbbccbbccbbccbc"
     */
    public String longestDiverseString(int a, int b, int c) {
        Map<Character, Integer> map = new HashMap<>();
        if (a > 0) map.put('a', a);
        if (b > 0) map.put('b', b);
        if (c > 0) map.put('c', c);
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        StringBuilder sb = new StringBuilder();
        
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            pq.offer(e);
        }
        
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> e = pq.poll();
            if (sb.length() < 2 || sb.charAt(sb.length() - 2) != e.getKey() || sb.charAt(sb.length() - 1) != e.getKey()) {
                sb.append(e.getKey());

                e.setValue(e.getValue() - 1);
                if (e.getValue() > 0) {
                    pq.offer(e);
                }
            } else {
                if (pq.isEmpty()) {
                    break;
                }
                
                Map.Entry<Character, Integer> next = pq.poll();

                sb.append(next.getKey());
                
                next.setValue(next.getValue() - 1);
                if (next.getValue() > 0) {
                    pq.offer(next);
                }
                
                pq.offer(e);
            }
        }
        
        return sb.toString();
    }
}