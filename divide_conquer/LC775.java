public class LC775 {
    /**
     * A = {2,5,1,4,3,0} 先将A拆分成左右两部分
     * left = {2,5,1} global = 2
     * right = {4,3,0} glbal = 3
     * 将左右进行排序变成{1,2,5}和{0,3,4}这时候再计算两边merge的global 右边0比左边1小 直接加上左边1以及后面的个数3个
     */
    public boolean isIdealPermutation(int[] A) {
        if (A == null || A.length == 0) return true;
        int n = A.length;
        int[] temp = new int[n];
        
        int local = 0;
        
        for (int i = 0; i < n - 1; i++) {
            if (A[i] > A[i + 1]) {
                local += 1;
            }
        }
        
        int global = mergesort(A, 0, n - 1, temp);
        
        return local == global;
    }
    
    private int mergesort(int[] A, int l, int r, int[] temp) {
        if (l >= r) return 0;
        
        int m = l + (r - l) / 2;
        
        int inv = mergesort(A, l, m, temp) + mergesort(A, m + 1, r, temp);
        int i = l, j = m + 1, k = 0;
        
        while (i <= m && j <= r) {
            if (A[i] > A[j]) {
                temp[k++] = A[j++];
                inv += m - i + 1;
            } else {
                temp[k++] = A[i++];
            }
        }
        
        while (i <= m) temp[k++] = A[i++];
        while (j <= r) temp[k++] = A[j++];
        
        int idx = 0;
        while (idx < k) {
            A[l++] = temp[idx++];
        }
        
        return inv;
    }

    /**
     * 因为数组是[0~n-1]的permutation，所以如果正常排序的话i == A[i]
     * 如果存在一个元素使得Math.abs(A[i] - i) > 1的话，这个数就是一个global的而不是local的 比如[]0, 3, 2, 1] [3,1]是global而不是local的
     */
    public boolean isIdealPermutation_v2(int[] A) {
        if (A == null || A.length == 0) return true;
        int n = A.length;
        int[] temp = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (Math.abs(A[i] - i) > 1) return false;
        }
        
        return true;
    }
}