class Solution {
    public int tribonacci(int n) {
        if (n < 3) return n == 0 ? 0 : 1;
        int temp = 0, x = 0, y = 1, z = 1;        
        
        for (int i = 3; i <= n; i++) {
            temp = x + y + z;
            x = y;
            y = z;
            z = temp;
        }
        
        return temp;
    }
}