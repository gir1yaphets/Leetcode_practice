class LC13 {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int pre = map.get(s.charAt(0));
        int sum = 0;
        
        for (int i = 1; i < s.length(); i++) {
            //curr不是为了相加 只是为了判断当前pre是不是比下一位数值小，如果是小的话需要减去pre(比如CM, C(100) < M(1000)先减去100然后再加上1000就是结果); 如果大的话加上pre
            //curr只是起比较的作用
            int curr = map.get(s.charAt(i));
            if (pre < curr) {
                sum -= pre;
            } else {
                sum += pre;
            }
            
            pre = curr;
        }
        
        //把最后一位加入到sum
        sum += pre;
        
        return sum;
    }
}