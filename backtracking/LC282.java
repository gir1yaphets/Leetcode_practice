import java.util.*;

class LC282 {
    private List<String> res = new ArrayList<>();
    
    public List<String> addOperators(String num, int target) {
        dfs(num, 0, target, "", 0, 1);
        return res;
    }
    
    private void dfs(String num, int index, int target, String path, long result, long multi) {
        if (index == num.length()) {
            if (result == target) {
                res.add(path);
            }
            return;
        }
        

        for (int i = index; i < num.length(); i++) {
            long curr = Long.parseLong(num.substring(index, i + 1));
            
            if (i > index && num.charAt(index) == '0') break;
            
            if (index == 0) {
                dfs(num, i + 1, target, curr + "", curr, curr);
            } else {
                dfs(num, i + 1, target, path + "+" + curr, result + curr, curr);
                
                dfs(num, i + 1, target, path + "-" + curr, result - curr, -curr);
                
                //比如3+4*5，在上一步中会先算result 3+4=7 但是遇到乘法要先算乘，所以result需要减去上一步的数字 再用上一步的数字乘以这一次的数即(7-4) + 4 *5 = 23
                dfs(num, i + 1, target, path + "*" + curr, result - multi + multi * curr, multi * curr);
            }
        }
    }

    /**
     * 优化版本: 
     * 1.用exp数组存储expression,避免了用一个string path在递归中进行反复拷贝
     * 2.用n = 10 *n + nums[i] 避免了反复取substring
     */
    private List<String> path = new ArrayList<>();
    
    public List<String> addOperators_better(String num, int target) {
        char[] exp = new char[num.length() * 2];
        dfs(num.toCharArray(), target, 0, exp, 0, 0, 0);
        return path;
    }
    
    private void dfs(char[] num, int target, int pos, char[] exp, int len, long curr, long pre) {
        if (pos == num.length) {
            if (curr == target) {
                path.add(new String(exp, 0, len));
            }
            return;
        }
        
        long n = 0;
        int l = len;
        
        int index = len;
        //如果pos != 0,说明这次不是第一个数，需要先填运算符号 但这个时候不能len++ 否则这个运算符号不能被回溯调，一直被记录在当前层
        if (pos != 0) {
            index = len + 1;
        }
        
        for (int i = pos; i < num.length; i++) {
            if (i > pos && num[pos] == '0') break;
            
            n = n * 10 + (num[i] - '0');
            
            if (n > Integer.MAX_VALUE) break;

            //这里需要在当前层+1，目的是为了保留之前的数字，比如123，在当前层加一以后可以记录1, 12, 123
            exp[index++] = num[i];
            if (pos == 0) {
                dfs(num, target, i + 1, exp, index, n, n);
            } else {
                exp[l] = '+';
                dfs(num, target, i + 1, exp, index, curr + n, n);
                
                exp[l] = '-';
                dfs(num, target, i + 1, exp, index, curr - n, -n);
                
                exp[l] = '*';
                dfs(num, target, i + 1, exp, index, curr - pre + pre * n, pre * n);
            }
        }
    }
}