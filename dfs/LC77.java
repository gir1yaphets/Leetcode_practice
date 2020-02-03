class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1, new ArrayList<>());
        return res;
    }

    private void dfs(int n, int k, int start, List<Integer> sol) {
        if (k == 0) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = start; i <= n - k + 1; i++) {
            sol.add(i);
            //这里可以进行剪枝，因为要凑满总共k个的集合，所以i最大能取到n-k+1,当总共有n个数字的时候，如果i=n-k+1，i~n有n-(n-k+1)=k个数字
            //但是随着每次取一个元素放到sol中，i可以取的值开始进行增大，所以这里将k-1给下一次递归,当k=2的时候(即还剩两个元素可以选)，i最大可以取n-1
            //当k=1的时候，i最大等于n，取到最后一个元素，下一次k就等于0了，将该组解加到res中
            dfs(n, k - 1, i + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
}