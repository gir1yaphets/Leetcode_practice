class LC774 {
    public double minmaxGasDist(int[] stations, int K) {
        int n = stations.length;
        int t = n + K;
        
        double start = stations[0], end = stations[n-1];
        
        double lower = 0, upper = end - start;
        double l = lower, r = upper;
        
        while (l + 1e-6 < r) {
            double m = (r + l) / 2;
            
            int count = 0;
            for (int i = 0; i < n - 1; i++) {
                //计算一下在staions[i+1]~staions[i]这个区间内，如果以m为间隔的建立station的话可以建几个
                //ceil是向上取整，(stations[i+1] - stations[i]) / m表示能分成几段，分割点的数量需要减1
                count += Math.ceil((stations[i+1] - stations[i]) / m) - 1;
            }
            
            if (count > K) {
                l = m;
            } else {
                r = m;
            }
        }
        
        return l;
    }
}