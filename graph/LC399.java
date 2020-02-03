import java.util.*;

class LC399 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("a", "b");
        List<String> list2 = Arrays.asList("b", "c");
        List<List<String>> eq = new ArrayList<>();
        eq.add(list1);
        eq.add(list2);

        double[] values = {2.0, 3.0};
        List<String> q1 = Arrays.asList("a", "c");
        List<List<String>> queries = new ArrayList<>();
        queries.add(q1);
        calcEquation(eq, values, queries);
    }

    private static Map<String, List<String>> pair = new HashMap<>();
    private static Map<String, List<Double>> valuePair = new HashMap<>();
    
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); i++) {
            List<String> item = equations.get(i);
            List<String> list1 = pair.getOrDefault(item.get(0), new ArrayList<>());
            List<Double> valueList1 = valuePair.getOrDefault(item.get(0), new ArrayList<>());
            
            List<String> list2 = pair.getOrDefault(item.get(1), new ArrayList<>());
            List<Double> valueList2 = valuePair.getOrDefault(item.get(1), new ArrayList<>());
            
            list1.add(item.get(1));
            list2.add(item.get(0));
            
            pair.put(item.get(0), list1);
            pair.put(item.get(1), list2);
            
            valueList1.add(values[i]);
            valueList2.add(1 /values[i]);
            
            valuePair.put(item.get(0), valueList1);
            valuePair.put(item.get(1), valueList2);
        }
        
        double[] res = new double[queries.size()];
        int index = 0;
        for (List<String> item : queries) {
            //dfs
            double result = dfs(item.get(0), item.get(1), 1.0, new HashSet<>());
            res[index++] = result;
        }
        
        return res;
    }
    
    private static double dfs(String s, String target, Double value, Set<String> visited) {
        if (!pair.containsKey(s)) {
            return -1.0;
        }

        if (s.equals(target)) {
            return value;
        }
        
        visited.add(s);
        
        double temp = -1.0;
        for (int i = 0; i < pair.get(s).size(); i++) {
            String nb = pair.get(s).get(i);
            if (visited.contains(nb)) {
                continue;
            }
            // value *= valuePair.get(s).get(i); 有错误
            temp = dfs(nb, target, value * valuePair.get(s).get(i), visited);
            
            if (temp != -1.0) {
                break;
            }
        }
        
        visited.remove(s);
        
        return temp;
    }
}