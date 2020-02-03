/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
import java.util.*;

class LC445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;

        //存储当前节点，算出当前位以后指向左边更高位
        ListNode list = new ListNode(0);
        
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int val1 = stack1.isEmpty() ? 0 : stack1.pop();
            int val2 = stack2.isEmpty() ? 0 : stack2.pop();
            
            sum += val1 + val2;
            
            //当前位的数值
            int digit = sum % 10;

            //当前位相加后是否有进位
            int carry = sum / 10;
            
            list.val = digit;
            
            //更高位node
            ListNode nextNode = new ListNode(carry);
            nextNode.next = list;
            list = nextNode;
            
            sum /= 10;
        }
        
        return list.val == 0 ? list.next : list;
    }
}