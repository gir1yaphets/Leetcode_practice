class LC1359 {
    public int countOrders(int n) {
        long res = 1, mod = 1000000007;
        //把所有的P进行全排列有n!中方法 然后开除插入D
        //D[n-1]有1种，把D[n-1]放到最后，D[n-2]有3种，D[0]有2n-1种
        for (int i = 1; i <= n; i++) {
            res = res * i * (2 * i - 1) % mod;
        }
        
        return (int) res;
    }
}