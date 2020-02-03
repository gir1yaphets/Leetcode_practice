import java.util.Map;

class LRUCache {
    class DLinkedNode {
        int key;
        int val;
        DLinkedNode next;
        DLinkedNode pre;
    }

    private DLinkedNode head, tail;
    
    private void addNode(DLinkedNode node) {
        node.next = head.next;
        node.pre = head;

        head.next.pre = node;
        head.next = node;
    }
    
    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode next = node.next;
        
        pre.next = next;
        next.pre = pre;
    }
    
    /**
     * 将某个节点移动到头
     * 当存在相同key的节点时调用
     * @param node
     */
    private void moveToHead(DLinkedNode node) {
        //将该节点从原来位置删除，然后在头部重新添加
        removeNode(node);
        addNode(node);
    }
    
    /**
     * 这里是移动倒数第二个节点 tail节点永远是最后一个
     */
    private DLinkedNode popTail() {
        DLinkedNode last = tail.pre;
        removeNode(last);
        return last;
    }
    
    private int cap;
    private Map<Integer, DLinkedNode> cache;
    
    public LRUCache(int capacity) {
        cap = capacity;
        cache = new HashMap<>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        
        moveToHead(node);
        
        return node.val;
    }
    
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node != null) {
            node.val = value;
            moveToHead(node);
            return;
        }
        
        
        if (cache.size() >= cap) {
            DLinkedNode last = popTail();
            cache.remove(last.key);
        } 
        
        DLinkedNode newNode = new DLinkedNode();
        newNode.key = key;
        newNode.val = value;
        
        cache.put(key, newNode);
        addNode(newNode);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */