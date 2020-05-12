/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
import java.util.*;
class LC364 {
    interface NestedInteger {
        boolean isInteger();
        int getInteger();
        List<NestedInteger> getList();
    }
    
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int weighted = 0, unweighted = 0;
        
        List<NestedInteger> list = nestedList;
        while (!list.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger n : list) {
                if (n.isInteger()) {
                    /**
                     * 将每一层的integer先加入到unweighed中，然后再加到weighted里，这样先进去的integer会被重复加n次
                     * e.g: [1, [4, [6]]]
                     * 第一次将1加入到unweighted, 然后将[4, [6]]加入到nextlevel list
                     * 当循环结束的时候将unweighted给weighted，然后再对[4, [6]]循环，当4遍历出来的时候 unweighted = 1 + 4 = 5
                     * 再将unweighted加到weighted中 这样1就被加了两次。
                     * 当6再解包的时候1被加了3次 4被加了两次
                     */
                    unweighted += n.getInteger();
                } else {
                    nextLevel.addAll(n.getList());
                }
            }
            
            weighted += unweighted;
            list = nextLevel;
        }
        
        return weighted;
    }
}