class LC959 {
    private int count = 0;
    
    /**
     * 将一个单元格拆成4个三角形区域 ，从上到下顺时针方向标记为0～3
     * 如果是 ‘/’ 就合并(0，3),(1, 2)
     * 如果是‘\\’ 就合并(0, 1),(2, 3)
     * 注意当前当前单元格可以和右边单元格和下边单元格合并 即当前1和右边3可以合并，因为没有竖着的分割线
     * 当前2可以和下边的0合并
     * 最终看有合并后还剩多少个单独不能合并的区域
     */
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        count = n * n * 4;
        int[] root = new int[count];
        
        for (int i = 0; i < count; i++) {
            root[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = 4 * (i * n + j);
                
                if (grid[i].charAt(j) != '/') {
                    union(index, index + 1, root);
                    union(index + 2, index + 3, root);
                }
                
                if (grid[i].charAt(j) != '\\') {
                    union(index, index + 3, root);
                    union(index + 1, index + 2, root);
                }
                
                if (j < n - 1) {
                    union(index + 1, index + 4 + 3, root);
                }
                
                if (i < n - 1) {
                    union(index + 2, index + 4 * n, root);
                }
            }
        }
        
        return count;
    }
    
    private int find(int x, int[] root) {
        if (x != root[x]) {
            root[x] = find(root[x], root);
        }
        
        return root[x];
    }
    
    private void union(int x, int y, int[] root) {
        int rootx = find(x, root);
        int rooty = find(y, root);
        
        if (rootx != rooty) {
            root[rooty] = root[x];
            count -= 1;
        }
    }
}