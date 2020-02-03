import java.util.*;
class LC945 {
    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length < 2) return 0;
        
        int move = 0;
        Arrays.sort(A);

        for (int i = 1; i < A.length; i++) {
            //这里<=是为了避免重复元素 e.g [1,2,2,3,3,3] -> [1,2,3,4,3] 最后这个3虽然不等于4，但是之前出现过3了
            //所以每次只要出现<=的元素,就要将它变成A[i-1]+1，那么这个元素变到A[i-1]需要的step是A[i-1] + 1 - A[i].
            if (A[i] <= A[i-1]) {
                move += A[i-1] + 1 - A[i];
                A[i] = A[i-1] + 1;
            }
        }

        return move;
    }
}