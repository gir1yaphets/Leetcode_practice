class LC1419 {
    public int minNumberOfFrogs(String frogs) {
        int n = frogs.length();
        char[] ca = frogs.toCharArray();
        int[] count = new int[5];
        int max = 0, frog = 0;//表示同时在叫的青蛙的数量
        
        for (int i = 0; i < n; i++) {
            char c = ca[i];
            int index = "croak".indexOf(c);
            //当前正在叫这个字母的青蛙个数增加1
            count[index] += 1;
            //'c'
            if (index == 0) {
                max = Math.max(max, ++frog);
            } else {
                //count[index - 1] == 0表示当前没有青蛙在叫之前那个字母，而直接叫了index这个字母
                if (count[index - 1] == 0) {
                    return -1;
                }

                count[index - 1] -= 1;
                //每次叫到k的，同时在叫的青蛙数量-1
                if (c == 'k') {
                    frog -= 1;
                }
            }
            
        }
        return frog == 0 ? max : -1;
    }
}