public class LC1328 {
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        char[] ca = palindrome.toCharArray();
        
        //把从左边第一个不是a的字符变成a
        for (int i = 0; i < n / 2; i++) {
            if (ca[i] != 'a') {
                ca[i] = 'a';
                return String.valueOf(ca);
            }
        }
        
        //如果全是a就把最后一个字母改成b
        ca[n - 1] = 'b';
        return n < 2 ? "" : String.valueOf(ca);
    }
}