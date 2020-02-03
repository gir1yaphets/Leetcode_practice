import java.util.*;

class LC23 {
    /**
     * T O(NlogK) k is the length of list node
     * N是总共有N个节点，k是lists数组的长度，注意这里并不是NlogN 因为pq中最多只有lists个数个节点 即k个
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        
        //注意不能在这里把list node的每一个节点添加到pq中，否则在pq.poll的时候会产生死循环
        //比如2->1 这样pq.poll会先出1,然后再出2，就会出现1->2->1...的情况 虽然list node是sorted，相同大小的node也会产生这种情况
        //所以只能将头节点先加入pq,然后出头节点，然后再加次节点，避免次节点先出pq,然后又手动控制指向头节点，头节点本身还在指向次节点的死循环
        for (ListNode l : lists) {
            if (l != null) {
                pq.offer(l);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        
        while (!pq.isEmpty()) {
            curr.next = pq.poll();
            curr = curr.next;
            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }
        
        return dummy.next;
    }
}