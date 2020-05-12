import java.util.*;
class LC339 {
    interface NestedInteger {
        boolean isInteger();
        int getInteger();
        List<NestedInteger> getList();
    }

    private int sum = 0;
    
    public int depthSum(List<NestedInteger> nestedList) {
        helper(nestedList, 1);
        return sum;
    }
    
    private void helper(List<NestedInteger> nested, int level) {
        for (NestedInteger n : nested) {
            if (n.isInteger()) {
                sum += n.getInteger() * level;
            } else {
                helper(n.getList(), level + 1);
            }
        }
    }
}