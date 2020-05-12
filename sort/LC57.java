class LC57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) return new int[][]{newInterval};
        List<int[]> list = new ArrayList<>();
        
        int[][] total = new int[intervals.length + 1][2];
        total[0] = newInterval;
        for (int i = 1; i < total.length; i++) {
            total[i] = intervals[i-1];
        }
        
        Arrays.sort(total, (o1, o2) -> o1[0] - o2[0]);
        
        for (int[] in : total) {
            if (list.size() == 0) {
                list.add(in);
                continue;
            }
            
            int[] last = list.get(list.size() - 1);
            if (last[1] < in[0]) {
                list.add(in);
            } else {
                last[1] = Math.max(last[1], in[1]);
            }
        }
        
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
}