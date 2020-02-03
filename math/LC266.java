class LC266 {
    public boolean canPermutePalindrome(String s) {
        if (s.length() == 0) return true;
        
        int[] map = new int[256];
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map[c] += 1;
        }
        
        int odd = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 == 1) {
                odd += 1;
            }
            
            if (odd > 1) {
                return false;
            }
        }
        
        return true;
    }
}