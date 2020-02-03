import java.util.*;

class LC127 {
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result = new LC127().ladderLength("hit", "cog", wordList);
        System.out.println(result);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> q = new LinkedList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        int level =  1;
        
        q.offer(beginWord);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                
                for (int j = 0; j < word.length(); j++) {
                    char[] letters = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        letters[j] = c;
                        String diff = new String(letters);
                        
                        if (wordSet.contains(diff)) {
                            if (diff.equals(endWord)) {
                                return level + 1;
                            }

                            q.offer(diff);
                            wordSet.remove(diff);//这里用list.remove会exception
                        }
                    }
                }
            }
            
            level += 1;
        }
        
        return 0;
    }
}