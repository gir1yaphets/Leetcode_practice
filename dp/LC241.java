import java.util.*;
class LC241 {
    /**
     * 将input找一个分割点，将计算结果分别加入两个list，然后分别计算
     */
    public List<Integer> diffWaysToCompute(String input) {
        int n = input.length();
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int curr = input.charAt(i);
            
            if (curr == '-' || curr == '+' || curr == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                
                for (int l : left) {
                    for (int r : right) {
                        if (curr == '-') {
                            list.add(l - r);
                        } else if (curr == '+') {
                            list.add(l + r);
                        } else if (curr == '*') {
                            list.add(l * r);
                        }
                    }
                }
            }
        }
        
        //如果在上面的循环中list没有被加入数据，说明当前分割的input中只包含数字，不包含运算符，直接加入该数字
        if (list.isEmpty()) {
            list.add(Integer.valueOf(input));
        }
        
        return list;
    }

    public List<Integer> diffWaysToCompute_memo(String input) {
        return dfs(input, new HashMap<>());
    }
    
    private List<Integer> dfs(String input, Map<String, List<Integer>> map) {
        int n = input.length();
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int curr = input.charAt(i);
            
            if (curr == '-' || curr == '+' || curr == '*') {
                String ls = input.substring(0, i), rs = input.substring(i + 1);
                
                List<Integer> left = map.containsKey(ls) ? map.get(ls) : dfs(ls, map);
                List<Integer> right = map.containsKey(rs) ? map.get(rs) : dfs(rs, map);
                
                for (int l : left) {
                    for (int r : right) {
                        if (curr == '-') {
                            list.add(l - r);
                        } else if (curr == '+') {
                            list.add(l + r);
                        } else if (curr == '*') {
                            list.add(l * r);
                        }
                    }
                }
            }
        }
        
        if (list.isEmpty()) {
            list.add(Integer.valueOf(input));
        }
        
        map.put(input, list);
        
        return list;
    }
}