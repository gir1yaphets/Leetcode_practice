import java.util.ArrayList;
import java.util.List;

class LC320 {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        backtrack(word, 0, res, sb, 0);
        return res;
    }
    
    private void backtrack(String word, int index, List<String> res, StringBuilder curr, int count) {
        if (index == word.length()) {
            res.add(curr.append(count > 0 ? count : "").toString());
            return;
        }
        
        int size = curr.length();
        
        //omit charAt(index)
        backtrack(word, index + 1, res, curr, count + 1);
        curr.setLength(size);
        
        //not omit charAt(index)
        backtrack(word, index + 1, res, curr.append(count > 0 ? count : "").append(word.charAt(index)), 0);
        curr.setLength(size);
    }
}