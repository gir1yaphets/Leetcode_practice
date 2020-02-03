/**
 * 这道题没有办法用Map<Integer, DoubleLinkedList>存储index和对应的节点
 * 因为当插入head的时候所有节点的index都会变
 */
class MyLinkedList {
    static class DoubleLinkedList {
        int val;
        DoubleLinkedList next;
        DoubleLinkedList prev;
        
        public DoubleLinkedList(int v) {
            this.val = v;
        }
    }

    /** Initialize your data structure here. */
    private DoubleLinkedList head, tail;
    private List<DoubleLinkedList> list;
    private int size;
    
    public MyLinkedList() {
        size = 0;
        
        head = new DoubleLinkedList(0);
        tail = new DoubleLinkedList(0);
        
        head.next = tail;
        tail.prev = head;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        
        DoubleLinkedList curr = head;
        
        while (index > 0) {
            curr = curr.next;
            index -= 1;
        }
        
        return curr.next.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        size += 1;
        DoubleLinkedList node = new DoubleLinkedList(val);
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        size += 1;
        DoubleLinkedList node = new DoubleLinkedList(val);
        node.next = tail;
        node.prev = tail.prev;
        
        tail.prev.next = node;
        tail.prev = node;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        
        if (index == size) {
            addAtTail(val);
            return;
        }
        
        if (index == 0) {
            addAtHead(val);
            return;
        }
        
        DoubleLinkedList curr = head;
        while (index > 0) {
            curr = curr.next;
            index -= 1;
        }
        
        DoubleLinkedList node = new DoubleLinkedList(val);
        node.next = curr.next;
        node.prev = curr;
        
        curr.next = node;
        curr.next.prev = node;
        
        size += 1;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        }
        
        size -= 1;
        
        DoubleLinkedList curr = head;
        while (index > 0) {
            curr = curr.next;
            index -= 1;
        }

        curr.next.next.prev = curr;
        curr.next = curr.next.next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */