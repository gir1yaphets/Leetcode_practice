class LC415 {
    public String addStrings(String num1, String num2) {
        int sum = 0, carry = 0;
        StringBuilder sb = new StringBuilder();
        
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry > 0; i--, j--) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            
            sum = x + y + carry;
            carry = sum / 10;
            sum %= 10;
            
            sb.append(sum);
        }
        
        return sb.reverse().toString();
    }
}