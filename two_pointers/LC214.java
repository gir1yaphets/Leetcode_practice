
public class LC214 {
    public static void main(String[] args) {
        new LC214().shortestPalindrome("abcbabcab");
    }

    /**
     * This problem asks us to add string before the input so the result string will be a palindrome.
     * We can convert it to an alternative problem"find the longest palindrome substring starts from index 0".
     * If we can get the length of such substring, then we can easily build a palindrome string by inserting the reverse part of substring after such substring before the original string.
     * 
     * Example:
     * input string:
     * abacd
     * longest palindrome substring starts from 0:
     * aba
     * Insert the reverse part of substring after palindrome substring before the head:
     * dcabacd
     */
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        String rev = sb.reverse().toString();
        
        for (int i = 0; i < n; i++) {
            /**
             * s   = abcbabcab
             * rev = bacbabcba
             * 当s的某个substring和rev的某个substring相等时，就是原来s中可以找到的回文串，
             * 这个时候在s前面单独加上s中不构成回文串部分的reverse就将整体构成了最短回文串
             */
            if (s.substring(0, n - i).equals(rev.substring(i))) {
                return rev.substring(0, i) + s;
            }
        }
        
        return "";
    }
}