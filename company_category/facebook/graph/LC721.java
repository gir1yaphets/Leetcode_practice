package graph;
import java.util.*;
/**
 * 构建graph，将每个email作为一个顶点，并将属于同一个人的email连接起来
 */
class LC721 {
    private Map<String, Set<String>> g = new HashMap<>();
    private Map<String, String> emailToName = new HashMap<>();
    private Set<String> emails = new HashSet<>();
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        
        if (accounts == null || accounts.size() == 0) return res;
        
        Set<String> visited = new HashSet<>();
        
        buildGraph(accounts);
        
        for (String e : emails) {
            List<String> related = new ArrayList<>();
            dfs(e, visited, related);
            
            Collections.sort(related);
            
            if (related.size() > 0) {
                List<String> account = new ArrayList<>();
                String name = emailToName.get(e);
                account.add(name);
                account.addAll(related);

                res.add(account);
            }
        }
        
        return res;
    }
    
    private void dfs(String start, Set<String> visited, List<String> related) {
        if (visited.contains(start)) return;
        
        visited.add(start);
        related.add(start);
        
        if (g.get(start) != null) {
            for (String nb : g.get(start)) {
                dfs(nb, visited, related);
            }
        }
    }
    
    private void buildGraph(List<List<String>> accounts) {
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                emails.add(acc.get(i));
                emailToName.put(acc.get(i), name);
                
                if (i == 1) continue;
                    
                String first = acc.get(i-1), second = acc.get(i);
                g.putIfAbsent(first, new HashSet<>());
                g.putIfAbsent(second, new HashSet<>());
                
                g.get(first).add(second);
                g.get(second).add(first);
            }
        }
    }


    /**
     * Union Find
     * 将不同的account根据email合并到一起
     */
    public List<List<String>> accountsMergeUf(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        
        if (accounts == null || accounts.size() == 0) return res;
        
        int n = accounts.size();
        
        int[] root = new int[n];
        
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        
        Map<String, Integer> emailToAccIndex = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            List<String> acc = accounts.get(i);
            for (int j = 1; j < acc.size(); j++) {
                String email = acc.get(j);
                
                //如果map中已经包含了这个email，则将两个account的index进行合并
                if (emailToAccIndex.containsKey(email)) {
                    int preIndex = emailToAccIndex.get(email);
                    
                    //将两个account的index进行合并
                    union(preIndex, i, root);
                } else {
                    emailToAccIndex.put(email, i);
                }
            }
        }
        
        Map<Integer, Set<String>> emailSet = new HashMap<>();
             
        for (int i = 0; i < n; i++) {
            //因为将不同的email根据index已经进行了合并，所以此处find的index就是同一个account的index，并将这些email都加到同一个set总
            int index = find(i, root);
            
            emailSet.putIfAbsent(index, new HashSet<>());
            
            for (int j = 1; j < accounts.get(i).size(); j++) {
                emailSet.get(index).add(accounts.get(i).get(j));
            }
        }
        
        for (int index : emailSet.keySet()) {
            List<String> item = new ArrayList<>();
            item.add(accounts.get(index).get(0));
            
            List<String> emailList = new ArrayList<>(emailSet.get(index));
            Collections.sort(emailList);
            item.addAll(emailList);
            res.add(item);
        }
        
        return res;
    }
    
    private int find(int x, int[] root) {
        if (x != root[x]) {
            root[x] = find(root[x], root);
        }
        
        return root[x];
    }
    
    private void union(int x, int y, int[] root) {
        root[find(x, root)] = root[find(y, root)];
    }
} 