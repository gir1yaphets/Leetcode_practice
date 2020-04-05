
public class LC309 {
    /**
     * rest[i]:第i天不操作获得的最大收益(休息+cooldown) = Math.max(res[i-1], sold[i-1])
     * hold[i]:第i天持有股票获得的最大收益 = Math.max(hold[i-1], rest[i] - prices[i])
     * sold[i]:第i天卖出股票的最大收益 = hold[i] + prices[i]
     * 注意sold[i]不能从rest[i-1]迁移过来，rest[i-1]可能不持有股票，所以只能从持有股票的状态迁移
     */
    public int maxProfit(int[] prices) {
        int sold = 0;
        int hold = Integer.MIN_VALUE;
        int rest = 0;
        
        for (int price : prices) {
            int preSold = sold;
            
            hold = Math.max(hold, rest - price);
            sold = hold + price;
            rest = Math.max(rest, preSold);
        }
        
        return Math.max(sold, rest);
    }
}