import java.util.*;

class LC127_2 {
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result = new LC127_2().ladderLength("hit", "cog", wordList);
        System.out.println(result);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> q = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> dist = new HashMap<>();
        
        int len = wordList.get(0).length();
        
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                //用*替换掉每一位，然后作为一个tag 将原来的word加到这个tag下面
                //比如hot -> *ot，将*ot作为一个tag，再把hot加到这个tag下面，下次遇到比如cot，也是*ot, 
                //那么这个*ot的key下面就有{hot, cot}
                String tag = word.substring(0, i) + "*" + word.substring(i+1, len);
                
                List<String> list = map.getOrDefault(tag, new ArrayList<>());
                list.add(word);
                map.put(tag, list);
            }
        }
        
        q.offer(beginWord);
        dist.put(beginWord, 1);
        
        while (!q.isEmpty()) {
            String word = q.poll();
            int d = dist.get(word);

            List<String> related = new ArrayList<>();
            //将这个字母替换掉一位的所有关联词找到，那当前word变换成这些词只需要换一个字母
            for (int j = 0; j < len; j++) {
                String head = word.substring(0, j) + "*" + word.substring(j+1, len);
                related.addAll(map.getOrDefault(head, new ArrayList<>()));
            }


            for (String r : related) {
                if (r.equals(endWord)) {
                    return d + 1;
                }

                if (!dist.containsKey(r)) {
                    q.offer(r);
                    dist.put(r, d + 1);
                }
            }
        }
        
        return 0;
    }
}