class LC12 {
    public String intToRoman(int num) {
        if (num < 1 || num > 3999) return "";
        
        //列举出所有的数字单元
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        int i = 0;
        StringBuilder sb = new StringBuilder();
        
        while (num > 0) {
            //如果num>=values[i], num可以减去一个这样的数据单元 比如3000, 那么num可以是MMM，存在3个M的数据单元
            while (i < values.length && num >= values[i]) {
                sb.append(romans[i]);
                num -= values[i];
                
            }
            
            //当num<values[i]的时候说明当前数据单元已经不够减了，所以i++找下一个小于当前的数据单元
            i++;
        }
        
        return sb.toString();
    }
}