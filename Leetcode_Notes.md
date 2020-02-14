## LeetCode Notes



### Note

+ 获取char数值的方法，不是ascii值: Character.getNumericValue(c);

+ PriorityQueue的add/offer, remove/poll方法时间复杂度都是**logN**

+ \w：与任何字符匹配，包括下划线，等价于"[A-Za-z0-9_]"

  \W：与任何非单词字符匹配, 等价于"[ ^A-Za-z0-9_]"

  ```String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split("\\s+");```

+ ```java
  //Map的values转list
  Map<String, List<String>> map;
  List<List<String>> list;
  list = new ArrayList<>(map.values());
  ```

+ ```java
  //digit恢复原数字
  String input = "123";
  int ans = 0;
  for (int i = 0; i < input.length(); i++) {
     int digit = (input.charAt(i) - '0');
     ans = ans * 10 + digit;
  }
  
  //数字求出每一位
  int num = 123;
  while (num != 0) {
     int digit = num % 10;
     num /= 10;
  }
  
  //int变量判断是否大于极限值，不能直接判断，判断ans*10+digit和Integer.MAX_VALUE的大小
  if (Integer.MAX_VALUE / 10 < ans 
      || Integer.MAX_VALUE / 10 == ans && Integer.MAX_VALUE % 10 < digit) {
                  return (sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE);
  }
  ```

+ Leetcode 641双向链表更新问题

  ```java
  public boolean deleteFront() {
      if (capacity == k) return false;
      capacity += 1;
  
      //错误写法，当只有一个有效节点的时候，如果先更新head.next = head.next.next，那么head.next = tail,此时再给调用head.next.next -> tail.next.pre直接crash
      //        head.next = head.next.next;
      //        head.next.next.pre = head;
  
      //正确，因为要调用next,所以不能先更新next
      head.next.next.pre = head;
      head.next = head.next.next;
  
      return true;
  }
  ```

+ List转array的时候不需要for循环赋值，可以用list.toArray()方法，但这样返回的是object类型，需要在toArray()中指定参数类型

  ```java
  int[][] res = list.toArray(new int[0][0]);
  ```

+ **Map按key或者value排序**

  ```java
  List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
  Collections.sort(list, (o1, o2) -> o1.getKey() - o2.getKey());
  ```

+ TreeMap

  + containsKey、get、put 和 remove 的时间复杂度是 log(n)

  + ```java
    //返回小于当前key的最大key，注意是类类型，Integer key = floorKey(key);当不存在的时候key==null
    public K floorKey(K key) {
        return keyOrNull(getFloorEntry(key));
    }
    ```

  + 类似的higherKey是返回大于当前key的最小key
  
+ Heap

  + 堆排序步骤(**ascending**): 
    + 1. 建立**大顶堆**：从最后一个root节点开始和它的左右节点进行比较，如果某一个节点比它大就和其交换，注意这个是个递归的过程，当某个被换下来的节点再次比它的左右节点小的时候需要继续对其进行调整
      2. 排序：建立好大顶堆以后，这个堆并不是有序的，所以需要继续进行排序。将堆顶元素和当前最后一个元素进行交换，这样就将最大元素放到了最后的位置，然后重新堆化，使剩余部分仍然是大顶堆。然后再将堆顶元素和倒数第二个元素进行交换，这样一直进行下去，整个数组就变成有序的了。
  + 堆插入元素：将元素插入到堆的尾部，然后进行堆化过程，即和它的左右节点比较，最终将其放到正确的位置上
  + 堆删除元素：删除头节点(可以理解为PQ.poll()操作)，将堆的最后一个元素移动到头部，然后开始进行堆化，将这个元素调整到正确的位置
  + 链接: https://blog.csdn.net/u011635492/article/details/83046477



### LinkedList

---

#### ListNode 23

> **链表和PriorityQueue结合**

```java
class LC23 {
    /**
     * T O(NlogK) k is the length of list node
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
```



#### Leetcode 445(链表加法)

```java
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
```



#### Leetcode 24

```java
class LC24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        
        while (curr.next != null && curr.next.next != null) {
            ListNode first = curr.next, second = curr.next.next;
            //让两个节点交换
            first.next = second.next;
            second.next = first;
            
            //用curr将所有的节点串起来
            curr.next = second;
            curr.next.next = first;
            
            curr = curr.next.next;
            
        }
        
        return dummy.next;
    }
}
```



#### Leetcode 148 (Sort list)

```java
class LC148 {
    public static ListNode sortList(ListNode head) {
        //注意这里是递归的终止条件，当head本身为null的时候直接返回null
        //同时如果当head只有一个节点的时候就直接把当前节点return就好了, 如果不return的话，当只剩下一个节点的时候进入死循环
        if (head == null || head.next == null) return head;
        
        ListNode slow = head, fast = head, pre = head;
        
        //1.cut list to 2 parts
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //将链表切断，否则下次递归的时候fast仍然会一直走到原始链表的尾部
        pre.next = null;
        
        //2. recursively cut until only one node left
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        
        //3.merge and sort
        return merge(l1, l2);
    }
    
    private static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        
        if (l2 == null) return l1;
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        if (l1 != null) {
            curr.next = l1;
        } else if (l2 != null) {
            curr.next = l2;
        }
        
        return dummy.next;
    }
}
```





### Tree

---

+ **最长路径问题**(不需要经过root): 需要维护两个值
  + 以当前节点为最高节点的最长路径(最大值就是最终结果)
  + 将当前节点左右节点的最长路径返回过父亲节点(左右节点只能选一个)
  + 关联题：124, 543, 687

#### Leetcode 124(max sum subtree)

求tree中某个sum最大的子树，不需要经过root

```java
private int max = Integer.MIN_VALUE;

public int maxPathSum(TreeNode root) {
  helper(root);
  return max;
}

/**
  * 需要记录两个值
  * 1.当前节点和其左右节点和的最大值，返回给上层，因为如果从上一层走下来只可能选择左边或者右边
  * 2.当前节点和其左右节点的和的最大值(注意如果left<0||right<0的时候是不要将小于0的值加到和里的)
  * 3.将2得到的值和全局max比较，更新max，e.g 当最终走到根节点的时候，判断是根节点最大，还是根节点加上左节点或者右节点或者3个同时加一起最大
     */
private int helper(TreeNode root) {
  if (root == null) {
    return 0;
  }

  //先将左右的返回值和0比较，这样就避免了第一种做法的每次需要3个值相加比较和
  int lmax = Math.max(helper(root.left), 0);
  int rmax = Math.max(helper(root.right), 0);

  //统计以当前节点为最高节点的sum值，并更新最大值
  max = Math.max(max, root.val + lmax + rmax);

  return root.val + Math.max(lmax, rmax);
}
```



#### Leetcode 235(LCA)

BST的最小公共祖先(LCA)

+ 如果p,q同时小于或者大于当前的根节点，利用BST性质，往左边或右边继续找

+ 当p,q某一个节点的值等于了根节点值, 或者p,q在某一节点上分叉了，那么该点就是LCA
+ 最坏时间 O(N) 该tree是list

```java
class LC235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;
        
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            //第一次遇到等于p或者等于q或者p,q分叉在root两边的node就是LCA
            return root;
        }
    }
}
```



#### Leetcode 236(LCA)

普通binary tree的Lowest common ancestor

```java
/**
 * LCA存在两种情况
 * 1. p, q一个在root左边一个在root右边，此时root就是LCA
 * 2. p, q都在root的同一侧，那么先找到的节点就是LCA
 */
class LC236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        //当找到一个节点的时候将当前节点返回给上层
        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //如果左边没有当前节点，说明p,q都在右边，返回右边先找到的节点作为common ancestor
        if (left == null) {
            return right;
        }

        //如果右边没有当前节点，说明p,q都在左边，返回左边先找到的节点作为common ancestor
        if (right == null) {
            return left;
        }

        //如果左边右边都不为null，说明p, q一个在左边一个在右边，所以此时lca应该是root
        return root;
    }
}
```



#### Leetcode 366(按顺序输出叶子节点)

bottom-up计数问题

```java
class LC336 {
    private List<List<Integer>> res = new ArrayList<>();
    
    /**
     * bottom->up开始记录层数，将对应层数节点的值加到list对应的index中
     * 每次向上返回的时候层数为左右较大值+1
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        helper(root);
        return res;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        //注意 这里不能再写if(root.isLeaves) return 0;
        //如果判断叶子节点的话，直接在叶子节点就返回了，就不会走到root==null
        int left = helper(root.left);
        int right = helper(root.right);
        
        //将左右level大的值作为当前节点的level
        int level = Math.max(left, right) + 1;
        
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }
        
      	//注意这个get在level == res.size()外，当前存在就直接取这一层的list
        res.get(level).add(root.val);
        
        return level;
    }
    
}
```



#### Leetcode 314(Vertical Order Traversal)

+ 错误DFS做法

```java
/**
 * 这道题不能用dfs做，用dfs保证不了节点加入list的顺序，因为先加入左子树，这样比如左子树的某个较深的右节点会先加入到list中，然后右子树较浅的节点才被加入
 * input: [3,9,8,4,0,1,7,null,null,null,2,5]
 * ouput: [[4],[9,5],[3,0,1],[2,8],[7]]
 * expected: [[4],[9,5],[3,0,1],[8,2],[7]]
 */
class LC314_wrongDFS {
    class Node {
        TreeNode node;
        int col;
        
        public Node(TreeNode node, int c) {
            this.node = node;
            col = c;
        }
    }
    
    private Map<Integer, List<Integer>> map = new TreeMap<>();
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        helper(new Node(root, 0));

        List<List<Integer>> res = new ArrayList<>(map.values());
        
        return res;
    }
    
    private void helper(Node root) {
        if (root.node == null) {
            return;
        }
        
        minCol = Math.min(minCol, root.col);
        
        List<Integer> list = map.getOrDefault(root.col, new ArrayList<>());
        list.add(root.node.val);
        map.put(root.col, list);
        
        helper(new Node(root.node.left, root.col - 1));
        helper(new Node(root.node.right, root.col + 1));
    }
}
```



+ 不用hashmap存储，直接计算offset

```java
class Solution {
    class Node {
        TreeNode node;
        int col;
        
        public Node(TreeNode node, int c) {
            this.node = node;
            col = c;
        }
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); //for root
        Queue<Node> q = new LinkedList<>();
        
        q.offer(new Node(root, 0));
        int minCol = 0, maxCol = 0;
        
        while (!q.isEmpty()) {
            Node n = q.poll();
            int col = n.col;
            TreeNode node = n.node;

            //add to head
            if (col < minCol) {
                minCol = col;
                
                List<Integer> leftList = new ArrayList<>();
                leftList.add(node.val);
                //遇到新的最小值，将该列加入到res的最前面
                res.add(0, leftList);
            } else if (col > maxCol) {
                List<Integer> rightList = new ArrayList<>();
                rightList.add(node.val);
                //直接加入到最后面
                res.add(rightList);
                
                //注意要在加入值后更新maxCol
                maxCol = col;
            } else {
                //col - minCol就是当前的偏移量
                res.get(col - minCol).add(node.val);
            }
            
            if (node.left != null) {
                q.offer(new Node(node.left, col - 1));
            }
            
            if (node.right != null) {
                q.offer(new Node(node.right, col + 1));
            }
        }
        
        return res;
    }
}
```



#### Leetcode 109(链表转平衡bst)

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

```java
/**
 * T: O(nlogn) : 找中点总共需要logn次 每次的时间 n/2 + 2 * n/4 + 4 * n/8...
 * S: O(logn)
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return helper(head, null);
    }
    
    private TreeNode helper(ListNode start, ListNode end) {
        if (start == end) return null;
        
        ListNode slow = start, fast = start;
        
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(start, slow);
        //这里start要指向slow.next, 否则当只有两个节点的时候fast和slow都不会移动，那么start永远不能到达end，导致stack over flow.
        root.right = helper(slow.next, end);
        
        return root;
    }
}
```



#### Leetcode 110(检验平衡二叉树)

是否是平衡二叉树

注意平衡二叉树的定义是任何一个点的左子树和右子树高度不相差超过1

并不是整棵树的最大高度和最小高度相差不超过1, 反例[1,2,2,3,3,3,3,4,4,4,4,4,4,null,null,5,5], 这棵树最大高度最小高度差是2，但是任意节点的左右子树高度差不超过1

```java
class LC110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        if (Math.abs(left - right) > 1) return false;
        
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(helper(root.left), helper(root.right)) + 1;
    }
}
```



#### Leetcode 987

按垂直方向输出tree node，相同行的两个值按照大小排序

```java
class Solution {
    class NodeCol {
        TreeNode node;
        int col;
        int row;
        
        public NodeCol(TreeNode n, int c, int r) {
            this.node = n;
            this.col = c;
            this.row = r;
        }
    }
    
    private int minCol = 0, maxCol = 0;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<List<NodeCol>> nodeList = new ArrayList<>();
        
        if (root == null) return res;
        
        Queue<NodeCol> q = new LinkedList<>();
        q.offer(new NodeCol(root, 0, 0));
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            NodeCol nc = q.poll();
            TreeNode node = nc.node;
            int col = nc.col;
            int row = nc.row;

            //为了后面将相同行的node按照大小排序，所以list中需要把行列信息都存住
            List<NodeCol> colList;
            if (col < minCol) {
                colList = new ArrayList<>();
                nodeList.add(0, colList);
                minCol = col;
            } else if (col > maxCol) {
                colList = new ArrayList<>();
                nodeList.add(colList);
                maxCol = col;
            } else {
                //注意根节点的情况
                if (nodeList.size() == 0) {
                    colList = new ArrayList<>();
                    nodeList.add(colList);
                } else {
                    colList = nodeList.get(col - minCol);
                }
            }

            colList.add(nc);

            if (node.left != null) {
                q.offer(new NodeCol(node.left, col - 1, row + 1));
            }

            if (node.right != null) {
                q.offer(new NodeCol(node.right, col + 1, row + 1));
            }
        }
        
        for (List<NodeCol> list : nodeList) {
            List<Integer> item = new ArrayList<>();
            //如果有相同行的两个元素，按照大小排序
            Collections.sort(list, (o1, o2) -> {
                if (o1.row == o2.row) {
                    return o1.node.val - o2.node.val;
                }
                
                return 0;
            });
            
            for (NodeCol nc : list) {
                item.add(nc.node.val);
            }
            res.add(item);
        }
        
        return res;
    }
}
```



#### Leetcode 113

输出和为sum的路径list

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return res;

        helper(root, 0, sum, new ArrayList<>());
        return res;
    }

    private void helper(TreeNode root, int sum, int target, List<Integer> sol) {
        if (root == null) return;

        sum += root.val;
        sol.add(root.val);

        if (root.left == null && root.right == null) {
            if (sum == target) {
                res.add(new ArrayList<>(sol));
                //当走到满足sum的叶子节点时，在这个if里面就回溯了，remove掉了自己，所以就不会再调用helper(left), helper(right)都返回null之后再remove，所以需要在这里进行回溯
                sol.remove(sol.size() - 1);
                return;
            }
        }

        helper(root.left, sum, target, sol);
        helper(root.right, sum, target, sol);

        sol.remove(sol.size() - 1);
    }
}
```



#### Leetcode 543(最长半径)

求最长半径，不一定要经过root

```java
class Solution {
    private int max = 0;

    /**
     * O(n)
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        //从低向上
        int left = helper(root.left);
        int right = helper(root.right);
        
        //统计每个节点的最长半径，既它的左子树高度 + 右子树高度
        max = Math.max(max, left + right);
        
        //对每个节点返回它的高度
        return Math.max(left, right) + 1;
    }
    
    /**
     * O(n^2)
     * @param root
     * @return
     */
    public int diameterOfBinaryTree_n2(TreeNode root) {
        if (root == null) {
            return max;
        }
        
        max = Math.max(max, height(root.left) + height(root.right));
        diameterOfBinaryTree(root.left);
        diameterOfBinaryTree(root.right);
        
        return max;
    }
    
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
```



#### Leetcode 687(相同值的节点的最长路径)

有相同值节点的最长路径，不需要过root

```java
/**
* 自己的把node节点通过返回值返回去，这个方法将父亲节点的值传下去
* @param root
* @param val
* @return
*/
private int helper(TreeNode root, int val) {
    if (root == null) return 0;

    int left = helper(root.left, root.val);
    int right = helper(root.right, root.val);

    //计算以当前节点为最高节点的最长高度
    //因为下面应判断了，当节点的值和父亲节点不相等的时候返回0，所以这里可以无条件更新max，不需要再判断是否和父亲节点相等
    max = Math.max(max, left + right);

    if (root.val == val) {
      //当前节点和父亲节点相同的时候更新当前节点的高度，
      return Math.max(left, right) + 1;
    }        

    return 0;
}
```



#### Leetcode 250(uni value)

```java
class Solution {
    private int count = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        helper(root);
        return count;
    }
    
    private boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        //如果是叶子节点的话该节点一定是uni value, count++
        if (root.left == null && root.right == null) {
            count += 1;
            return true;
        }
        
        boolean left = helper(root.left);
        boolean right = helper(root.right);
        
        //如果左子树或者右子树并不是uni value的，那么当前节点一定不是，直接返回false
        if (!left || !right) {
            return false;
        }
        
        //当前节点和左右节点值不相同的时候不是uni value
        if (root.left != null && root.left.val != root.val || root.right != null && root.right.val != root.val) {
            return false;
        }
        
        count += 1;
        return true;
    }
}
```





### Graph

---

#### Leetcode 802

判断有向图中哪些点不存在环

```java
class Solution {
    /**
     * 判断有向图是否存在环问题
     * visited[i] = 2,代表i在某一个环中，visited[i] = 1代表i访问过并且不在环中
     * 每次先标记的访问的节点为2，当dfs过程中遇到一个访问过的节点的时候，如果该点等于2那么dfs返回false，并把之前一路上过来的节点都返回false
     * 当该节点在dfs的相邻接点中都没有返回false，则将该节点标记为1
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        List<Integer> res = new ArrayList<>();
        if (n <= 0) return res;
        
        for (int i = 0; i < n; i++) {
            //dfs
            if (dfs(graph, i, visited)) {
                res.add(i);
            }
        }
        
        return res;
    }
    
    private boolean dfs(int[][] graph, int start, int[] visited) {
        //如果该节点已经被访问过，那么该点不能是2 比如 1->2<-3 当访问到2的时候，2的visited已经被标记称了1
        if (visited[start] != 0) {
            return visited[start] == 1;
        }
        
        visited[start] = 2;
        for (int nb : graph[start]) {
            
            if (!dfs(graph, nb, visited)) {
                return false;
            }
        }
        
        visited[start] = 1;
        
        return true;
    }
}
```



### DFS

---

#### Leetcode 210

排课问题，可以用拓扑排序和DFS做

> 1. dfs判断环的过程中不能简单判断visited[i] = true，因为叶子节点可能被访问多次(1和3都指向2, 1->2<-3), 所以需要用0,1,2判断是否是叶子节点被访问多次还是某个点存在环。
>
> 2. 当遍历完邻接点以后最先出来的就是叶子节点，再是其他相邻节点都遍历完成的节点
> 3. 不需要考虑连通问题，不连通的节点就是没有预先课程

```java
// T: O(n); S: O(n) 每个节点最多访问一遍
class Solution {
    private List<Integer> list = new ArrayList<>();
    private boolean hasCycle = false;
    
    public int[] findOrder(int n, int[][] pre) {
        int[] res = new int[n];
        //当没有预先课程条件的时候，每个课程的预先课程就是自己本身
        if (pre.length == 0) {
            for (int i = 0; i < n; i++) {
                res[i] = i;
            }
            return res;
        }
        
        List<Integer>[] table = new ArrayList[n];
        int[] visited = new int[n];
        
        //构建有向图
        for (int[] v : pre) {
            //vi -> vo
            int vi = v[1], vo = v[0];
            
            if (table[vi] == null) {
                table[vi] = new ArrayList<>();
            }
            table[vi].add(vo);
        }
        
        for (int i = 0; i < n; i++) {
            //dfs
            if (visited[i] == 0 && !hasCycle) {
                dfs(table, i, visited);
            }
        }
        
        if (hasCycle) return new int[0];
        
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(list.size() - i - 1);
        }
        
        return res;
    }
    
    private void dfs(List<Integer>[] table, int start, int[] visited) {
        visited[start] = 1;
        if (table[start] != null) {
            for (int nb : table[start]) {
                //如果在以某个点做dfs的过程中遇到了visited[nb] == 1，则一定是环,直接return(因为是有向图，如果不存在连接的话不回访问前一节点)
                //如果没有在过程中发现环，则从最后一个节点开始往回溯标记visited[start]
                if (visited[nb] == 1) {
                    hasCycle = true;
                    return;
                } else if(visited[nb] == 0) {
                    dfs(table, nb, visited);
                }
            }
        }
        
        //当相邻节点都访问完以后，加到list中，最先加进去的肯定是出度为0的叶子结点，就是最后才可以上的课程
        //也就是先加进list的是最后才可以上的课
        //visited == 2的是叶子节点，可以有多个点指向这个叶子节点 所以不是环
        //但如果某一个点不是叶子节点，并且在dfs过程中还可以被访问第二次，则存在环
        visited[start] = 2;
        list.add(start);
    }
}
```



#### Leetcode 39

搜索所有等于sum的组合

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0, 0, new ArrayList<>());
        return res;
    }
    
    /**
     * 需要用start记录当前遍历的其实位置，否则会出现解的排列组合
     * [2,3,6,7] 当搜索到3的时候避免再回去搜索2，因为2的所有解都已经搜索完了
     * @param can
     * @param target
     * @param sum
     * @param start
     * @param sol
     */
    private void dfs(int[] can, int target, int sum, int start, List<Integer> sol) {
        if (sum == target) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = start; i < can.length; i++) {
            //放在这里而不是方法开头，避免一次递归
            if (sum + can[i] > target) {
                continue;
            }
            
            sol.add(can[i]);
            dfs(can, target, sum + can[i], i, sol);
            sol.remove(sol.size() - 1);
        }
    }
}
```



#### Leetcode 40

在39题的基础上去掉重复元素和重复解

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<>());
        return res;
    }
    
    private void dfs(int[] can, int target, int sum, int start, List<Integer> sol) {
        if (sum == target) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = start; i < can.length; i++) {
            if (sum + can[i] > target) {
                continue;
            }
            
            //当遇到重复的元素不放到list中
            if (i > start && can[i] == can[i - 1]) {
                continue;
            }
            
            sol.add(can[i]);
            //下一个元素不能使用当前元素，所以i+1作为下次dfs的起点
            dfs(can, target, sum + can[i], i + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
}
```



#### Leetcode 78

```java
/**
 * T: O(2^n) 对于每一个数字，可以选或者不选，既有两种选择
 * S: O(n) 最多的递归层数是每一个数字都选
 */
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return res;
    }
    
    private void dfs(int[] nums, int start, List<Integer> sol) {
        //注意这个要在结束条件之前，否则最后一次加不进去
        res.add(new ArrayList<>(sol));
        
        if (start == nums.length) {
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            sol.add(nums[i]);
            dfs(nums, i + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
}
```



#### Leetcode 77

> Given two integers *n* and *k*, return all possible combinations of *k* numbers out of 1 ... *n*

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1, new ArrayList<>());
        return res;
    }

    private void dfs(int n, int k, int start, List<Integer> sol) {
        if (k == 0) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = start; i <= n - k + 1; i++) {
            sol.add(i);
            //这里可以进行剪枝，因为要凑满总共k个的集合，所以i最大能取到n-k+1,当总共有n个数字的时候，如果i=n-k+1，i~n有n-(n-k+1)=k个数字
            //但是随着每次取一个元素放到sol中，i可以取的值开始进行增大，所以这里将k-1给下一次递归,当k=2的时候(即还剩两个元素可以选)，i最大可以取n-1
            //当k=1的时候，i最大等于n，取到最后一个元素，下一次k就等于0了，将该组解加到res中
            dfs(n, k - 1, i + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
}
```



#### Leetcode 47

产生没有重复集合的permutation

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) return res;
        
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        
        dfs(nums, visited, new ArrayList<>());
        
        return res;
    }
    
    private void dfs(int[] nums, boolean[] visited, List<Integer> sol) {
        if (sol.size() == nums.length) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            //e.g [1,1,2] 正常情况第一个1使用过，这个时候可以使用第二个1
            //但当递归到第二个1的时候，若前一个元素与它相等，并且还没有被使用过，那么这个元素就不需要再使用，因为再使用这个元素作为新的起点会和它前一个元素差生一样的集合
            if (visited[i] || (i > 0 && !visited[i - 1] && nums[i] == nums[i-1])) {
                continue;
            }
            
            visited[i] = true;
            sol.add(nums[i]);
            
            dfs(nums, visited, sol);
            
            visited[i] = false;
            sol.remove(sol.size() - 1);
        }
    }
}
```



#### Leetcode 212

dfs与字典数结合

+ 超时做法，记录一个String curr,然后将当前char append进来，然后调用字典树的search方法从头找
+ 第一次dfs的时候不能入空字符串，直接找第一个

```java
import java.util.*;
class Solution {
    private int[][] dir =  {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    private List<String> res = new ArrayList<>();
    
    public List<String> findWords(char[][] board, String[] words) {
        
        for (String s : words) {
            insert(s);
        }
        
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //dfs 这里不能入‘’值，直接找第一个字母
                dfs(board, i, j, board[i][j], root);
            }
        }
        
        return res;
    }
    
    public void dfs(char[][] board, int startx, int starty, char c, TrieNode t) {
        if (t.children[c - 'a'] == null) {
            return;
        }
        
        //trie的root节点是个虚节点，所以要先找到第一个包含字母的children
        t = t.children[c - 'a'];
        
        if (t.word != null && !res.contains(t.word)) {
            res.add(new String(t.word));
            t.word = null;
            // return; ***这里不能return，因为可能存在相同前缀的词，所以找到一个以后还要继续找
        }

        char letter = board[startx][starty];
        board[startx][starty] = '#';
        
        for (int[] d : dir) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == '#') {
                continue;
            }
            
            dfs(board, x, y, board[x][y], t);
        }
        
        board[startx][starty] = letter;
        
    }
    
    class TrieNode{
        String word;
        TrieNode[] children;
        
        public TrieNode() {
            word = null;
            children = new TrieNode[26];
        }
    }
    
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode curr = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }

            curr = curr.children[c - 'a'];
        }

        curr.word = word;
    }
}
```



### BFS

---

#### Leetcode 1102

BFS + PriorityQueue

```java
class Solution {
    private int[][] dir = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    class Cell {
        int r;
        int c;
        int val;
        
        public Cell(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.val = v;
        }
    }
    public int maximumMinimumPath(int[][] A) {
        int m = A.length, n = A[0].length;
        
        //维护一个大顶堆，每次从堆顶拿最大cell的值
        //有一种情况比如走[1, 4, 2, 1], [3,3,3,3]
        //第一次在3和4之间选择4，但是沿着4走会走到2和1，不满足要求，这个时候大顶堆会把第二行的3 poll出来沿着3继续走
        //所以并不会向贪心算法一样每次只选择最大的值，但是最终结果不满足要求
        PriorityQueue<Cell> pq = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);
        boolean[] visited = new boolean[m * n];
        
        pq.add(new Cell(0, 0, A[0][0]));
        visited[0] = true;
        int min = Math.min(A[0][0], A[m-1][n-1]);
        
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            int r = cell.r, c = cell.c, val = cell.val;

            //因为poll出来的都是当前最大的点，所以直接更新min
            min = Math.min(min, val);

            for (int[] d : dir) {
                int nr = r + d[0], nc = c + d[1];
                int npos = nr * n + nc;
                if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[npos]) {
                    continue;
                }

                if (nr == m - 1 && nc == n - 1) {
                    return min;
                }

                visited[npos] = true;
                pq.add(new Cell(nr, nc, A[nr][nc]));
            }
        }
        
        return min;
    }
}
```



#### Leetcode 505

maze#2

```java
class LC505 {
    private int[][] dir = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
    
  	/**
  	* 用bfs更新所有转折点的距离
  	*/
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> q = new LinkedList<>();
        
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }
            
        q.offer(start);
        dist[start[0]][start[1]] = 0;
        
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int dis = dist[p[0]][p[1]];
            
            for (int[] d : dir) {
                int count = 0;
                
                int x = p[0] + d[0];
                int y = p[1] + d[1];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += d[0];
                    y += d[1];
                    
                    count++;
                }
                
                x = x - d[0];
                y = y - d[1];
                
                if (dist[x][y] == -1 || dist[x][y] > count + dist[p[0]][p[1]]) {
                    q.offer(new int[]{x, y});
                    dist[x][y] = dis + count;
                }
            }
        }
        
        return dist[destination[0]][destination[1]] == -1 ? -1 : dist[destination[0]][destination[1]];
    }
}
```



#### Leetcode 269(Alien Dictionary)

```java
import java.util.*;

class LC269 {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> g = new HashMap<>();
        int[] indegree = new int[26];
        buildGraph(words, g, indegree);
        return bfs(g, indegree);
    }
    
    private void buildGraph(String[] words, Map<Character, Set<Character>> g, int[] indegree) {
        //将所有出现过的字母作为graph的一个顶点
        for (String word : words) {
            for (char c : word.toCharArray()) {
                g.putIfAbsent(c, new HashSet<>());
            }
        }
        
        for (int i = 1; i < words.length; i++) {
            //相邻两个字符串进行字母比较
            String first = words[i - 1];
            String second = words[i];
            
            int minLen = Math.min(first.length(), second.length());
            
            for (int j = 0; j < minLen; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);
                if (out != in) {
                    //Map的key是set 不会重复添加 但是indegree不能加多次
                    if (!g.get(out).contains(in)) {
                        g.get(out).add(in);
                        indegree[in - 'a']++;
                    }
                    //只要发现一个不同的就确定了一个关系 后面的则不能再比较了 因为第一个的不同的字符决定了顺序
                    break;
                }
            }
        }
    }
    
    private String bfs(Map<Character, Set<Character>> g, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        
        for (char c : g.keySet()) {
            if (indegree[c - 'a'] == 0) {
                q.offer(c);
            }
        }
        
        while (!q.isEmpty()) {
            char out = q.poll();
            sb.append(out);
            
            if (g.get(out) != null && g.get(out).size() > 0) {
                for (char in : g.get(out)) {
                    if (--indegree[in - 'a'] == 0) {
                        q.offer(in);
                    }
                }
            }
        }
        
        return sb.length() == g.size() ? sb.toString() : "";
    }
}
```





### Backtracking

---

#### Leetcode 78

**一定注意最终加入到res的需要new ArrayList，否则都被remov掉了**

```java
//Subset
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return res;
    }
    
    private void dfs(int[] nums, int start, List<Integer> sol) {
        res.add(new ArrayList<>(sol));
        
        for (int i = start; i < nums.length; i++) {
            sol.add(nums[i]);
            dfs(nums, i + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
}
```



#### Leetcode 46

```java
//Permutation
class LC46 {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<>(), visited);
        return res;
    }
    
    /**
     * 不需要用start记录起点 每次进入dfs都对整个数组进行遍历 找一个没用过的add到solution里
     * @param nums
     * @param sol
     * @param visited
     */
    private void dfs(int[] nums, List<Integer> sol, boolean[] visited) {
        if (sol.size() == nums.length) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sol.add(nums[i]);
                
                dfs(nums, sol, visited);
                
                visited[i] = false;
                sol.remove(sol.size() - 1);
            }
        }
    }
}
```



#### Leetcode 77

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combine(int n, int k) {
        //dfs
        dfs(n, k, 1, new ArrayList<>());
        return res;
    }
    
    private void dfs(int n, int k, int start, List<Integer> sol) {
        if (k == sol.size()) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            sol.add(i);
            dfs(n, k, i + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
}
```



#### Leetcode 131

找到所有的palindrome

```java
class LC131 {
    public List<List<String>> partition(String s) {
        List<String> item = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        
        dfs(s, 0, item, res);
        return res;
    }
    
    private void dfs(String s, int start, List<String> item, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(item));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            //先从起点开始找到一个palindrome，然后以该点为起点继续做dfs，需要进行backtracking找到所有可能性
            if (isPalindrome(s, start, i)) {
                item.add(s.substring(start, i + 1));
                dfs(s, i + 1, item, res);
                item.remove(item.size() - 1);
            }
        }
    }
    
    /**
     * 从两边开始向中间确认
     * @param s
     * @param left
     * @param right
     * @return
     */
    private boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            
            left++;
            right--;
        }
        q
        return true;
    }
}
```





### DP

---

#### Leetcode 139

***将字符串拆成两部分，用dp记录前半部分是否能切割，然后check后半部分单词是否在字典中***，这种方法很常见

- dp[i]: [0~i-1]能否被拆分开

- dp[i] = dp[j] + wordList.contains(s.substring(j, i))

  - dp[i] = dp[0] && inDict(s[0...i-1]) || dp[1] && inDict(s[1...i-1]) || ... || dp[j] && inDict(s[j...i-1]) || dp[i-1] && inDict(s[i-1]) (0 <=j <= i -1)

  ​	即如果i这么长的字符串在wordList中，那么j这么长的字符串在wordList中 并且substring(j, i)这个单词存在	于wordList中

```java
public boolean wordBreak(String s, List<String> wordDict) {
    int len = s.length();
    boolean[] dp = new boolean[len + 1];

    dp[0] = true;

    for (int i = 1; i <= len; i++) {
      for (int j = 0; j < i; j++) {
        //注意dp[j]表示的是0~j，所以不包含j本身,后面的substring应该从j~i，而不是j+1~i
        //比如"leetcode" ["leet", "code"]，当遇到leet的时候,是dp[4]=true;而不是dp[3]=true
        if (dp[j] && wordDict.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[len];
}
```

```java
/**
剪枝: 当判断分割点右边的字符串是否在dict中时，先判断一下是否大于dict中最大字符串的长度
*/
public boolean wordBreak_prune(String s, List<String> wordDict) {
    int len = s.length();
    boolean[] dp = new boolean[len + 1];

    int maxLen = 0;

    for (String word : wordDict) {
      maxLen = Math.max(maxLen, word.length());
    }

    dp[0] = true;

    for (int i = 1; i <= len; i++) {
      //注意需要判断一下i-maxLen和0的大小
      //因为最大长度是len，那么只需要判断从i开始往前len个长度即可 比如"leetcode“，当i指向e后面的时候(i=9), j没有必要从0开始，因为l~e的长度已经超过最大的长度
      //肯定不可能存在这样的substring，所以只需要从i往前4个单位从c开始即可
      for (int j = Math.max(0, i - len); j < i; j++) {
        if (dp[j] && wordDict.contains(s.substring(j, i))) {
          dp[i] = true;
        }
      }
    }

    return dp[len];
 }
```



*记忆递归*

```java
/**
* 和dp的方向正好相反 先检查左边字符串是否包含，再递归检查右边是否能被拆分，如果可以记录当前在memo[start]这一点可以拆分
*/
public boolean wordBreak_memo(String s, List<String> wordDict) {
  	return helper(s, wordDict, 0, new Boolean[s.length()]);
}

private boolean helper(String s, List<String> wordDict, int start, Boolean[] memo) {
     if (start == s.length()) {
       	return true;
     }

     if (memo[start] != null) {
       	return memo[start];
     }

     for (int pos = start + 1; pos <= s.length(); pos++) {
       //check left side whether contained in dict
       if (!wordDict.contains(s.substring(start, pos))) {
         	continue;
       }

       //check right side is able to be segmented recursively
       if (helper(s, wordDict, pos, memo)) {
         	//可以在start这一点分割
         	memo[start] = true;
         	return true;
       }
     }

     memo[start] = false;
     return false;
}
```



#### Leetcode 140

这道题直接用dfs也会TLE, 所以也需要进行记忆递归，记忆递归需要通过返回值将存住的结果直接返回去

```java
		private Map<Integer, List<String>> map = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
      	return dfs(s, wordDict, 0);
    }

    public List<String> dfs(String s, List<String> wordDict, int start) {
        if (map.containsKey(start)) {
          return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
          res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
          if (wordDict.contains(s.substring(start, end))) {
            List<String> list = dfs(s, wordDict, end);
            for (String l : list) {
              res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
            }
          }
        }
        map.put(start, res);
        return res;
		}
```



#### Leetcode 322(背包问题)

dp\[i][j]表示使用前i种不同硬币达到amount的最小数量

初始条件: dp\[0][0] = 0; dp\[0][j] = Integer.MAX_VALUE;

```java
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];//dp[i]:构成amount==i需要的最少的硬币数量，因为最终是要构成amount，所以dp数组的大小应该是amount+1
    Arrays.fill(dp, amount + 1);//初始化成最大值
    dp[0] = 0;//得到amount=0只需要0枚硬币

  	//对于每一个coin能组成的amount值
    for (int i = 0; i < coins.length; i++) {
      //如果j<coins[i],那么coins[i]肯定不可能组成amount=j,所以j从coins[i]开始
      for (int j = coins[i]; j <= amount; j++) {
        //组成amount=j,要么不选当前这么硬币coins[i]，要么选上当前这枚硬币coins[i],如果要选当前这枚硬币，则最小的组成是amount-coins[i]的硬币数量加上当前这枚硬币
        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
      }
    }

    return dp[amount] >= amount + 1 ? -1 : dp[amount];
}
```



```java
public int coinChange_2(int[] coins, int amount) {
    int[] dp = new int[amount + 1];

    for (int i = 1; i <= amount; i++) {
      dp[i] = amount + 1;
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }

    return dp[amount] >= amount + 1 ? -1 : dp[amount];
}
```



#### Leetcode 1137

```java
class Solution {
    public int tribonacci(int n) {
        if (n < 3) return n == 0 ? 0 : 1;
        int temp = 0, x = 0, y = 1, z = 1;        
        
        for (int i = 3; i <= n; i++) {
            temp = x + y + z;
            x = y;
            y = z;
            z = temp;
        }
        
        return temp;
    }
}
```



#### Leetcode 70

爬楼梯问题，滚动数组

```java
class Solution {
    public int climbStairs(int n) {
        if (n < 2) return n == 1 ? 1 : 0;
        
//         int[] dp = new int[n+1];
        
//         dp[0] = 1;
//         dp[1] = 1;
//         for (int i = 2; i <= n; i++) {
//             dp[i] = dp[i-1] + dp[i-2];
//         }
        
//         return dp[n];
        
        int x = 1, y = 1;
        int res = 0;
        
        for (int i = 2; i <= n; i++) {
            res = x + y;
            x = y;
            y = res;
        }
        
        return res;
    }
}
```



#### Leetcode 53

求subarray的和的最大值

```java
class LC53 {
    /**
     * dp[i]:从0~i的subarray的和的最大值
     * 整体的最大值不一定出现在dp[n-1] 
     * e.g [-2,1,-3,4,-1,2,1,-5,4] 到index 6的最大值是6 但是到index 7的时候subarray最大值是1
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        int[] dp = new int[n];
        
        dp[0] = nums[0];
        int max = dp[0];
        
        for (int i = 1; i < n; i++) {
            //到index i的和最大的subarray 要么是dp[i-1]在连上当前nums[i],要么是nums[i]比之前的subarray的和都大，直接以nums[i]自己单独为subarray
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        
        return max;
    }
}
```



#### Leetcode 300(LIS)

最长上升子序列LIS，两种做法dp和二分+dp

```java
import java.util.*;

class LC300 {
    /**
     * dp[i] 以i为长度的数组中最长的上升子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        int max = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    //因为j是从0~i, 所以会反复更新dp[i],选择长度最大的
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }

    /**
    https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
     * 维护一个tail数组，记录当前长度的结尾数字 这个tail数组一定单调递增
     * 遍历整个nums数组，当遇到一个数字比tail中最后一个数字大的时候，记录该数字，并更新tail数组size
     * 如果遇到一个数字比当前tail数组中某一个数字小，则更新这个数字
     * 这样做的原因是，每次找到最小值替换掉tail中较大的 这样才能保证找到最长的上升序列
     * [10,9,2,5,3,7,101,18]
     * len: 1 [10] tail = [10]
     * len:   [10]不要,[9] tail = [9]
     * len:   [10],[9]不要,[2] tail = [2]
     * len: 2 [10],[9],[2],[5] tail = [2],[5]
     *        [10],[9],[2],[5]不要 [3] tail = [2],[3]
     * len: 3 [10],[9],[2],[5]不要 [3],[7] tail = [2],[3],[7]
     * @param nums
     * @return
     */
    public int lengthOfLIS_Dp_BinarySearch(int[] nums) {
        int n = nums.length;
        
        int[] tail = new int[n];
        int size = 0;
        
        for (int num : nums) {
            int lo = 0, hi = size;
            //在tail中找到一个数使num刚好大于它，如果不存在就加到最后了
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tail[mid] < num) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            
            tail[lo] = num;
            
            if (lo == size) {
                size++;
            }
        }
        
        return size;
    }
}
```



#### Leetcode 122(股票问题)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 0) return 0;
        
        //dp[i][0]:第i天持有0股的可以获得的利润; dp[i][1]:第i天持有1股可获得的利润
        int[][] dp = new int[n][2]; 
        
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        
        int max = 0;
        
        for (int i = 1; i < n; i++) {
            //第i天持有0股的利润 = Max(第i-1天持有0股的利润， 第i-1天持有1股并且在第i天卖出获得的利润)
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]); //dp[i][1]不可能是最大利润，只是为了计算dp[i-1][1]用
            
            max = Math.max(dp[i][0], max);
        }
        
        return max;
    }
}
```



#### 63. Unique Paths II

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] ob) {
        if (ob.length == 0 || ob[0].length == 0) return 0;
        int m = ob.length, n = ob[0].length;
        
        int[][] dp = new int[m][n];
        
        dp[m-1][n-1] = 1 - ob[m-1][n-1];
        
        for (int j = n - 2; j >= 0; j--) {
            dp[m-1][j] = (ob[m-1][j] == 1 || dp[m-1][j+1] == 0) ? 0 : 1;
        }
        
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n-1] = (ob[i][n-1] == 1 || dp[i+1][n-1] == 0) ? 0 : 1;
        }
        
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = ob[i][j] == 1 ? 0 : dp[i+1][j] + dp[i][j+1];
            }
        }
        
        return dp[0][0];
    }
}
```



#### 304. Range Sum Query 2D - Immutable

![截屏2020-02-11下午4.13.56](capture/截屏2020-02-11下午4.13.56.png)



![截屏2020-02-11下午4.14.34](capture/截屏2020-02-11下午4.14.34.png)



```java
class LC304 {
    private int[][] dp;
    
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0) return;
        
        int m = matrix.length, n = matrix[0].length;
        dp = new int[m+1][n+1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        sum = dp[row2+1][col2+1] - dp[row1][col2+1] - dp[row2+1][col1] + dp[row1][col1];
        
        return sum;
    }
}
```



#### Leetcode 221.Maximal Square

> dp\[i]\[j]: \[0][0] ~ \[i][j]中最长的全是1的边长
>
> 由它左，上，左上三个点中的最小值决定，最小值+1就是当前点的最大边长

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        
        int[][] dp = new int[m][n];
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                
                max = Math.max(max, dp[i][j]);
            }
        }
        
        return max * max;
    }
}
```





### Pre sum问题

#### Leetcode 560(Subarray Sum Equals K)

```java
		/**
     * 将(j, i) = k拆为两部分 (0, i) = sum和(0, j-1) = sum - k; 
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int sum = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
      
	      //初始化前缀和为0的个数为1, 为了加入sum == k的情况
        map.put(0, 1);
        
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            
            /**
             * 0～i的和是sum, 判断i之前有多少个前缀和从(0 ~ j-1)是sum - k
                那么就存在多少个从j~i的和是k, 这里和位置无关
                e.g {x, x, x, x, x, x} k = 2
                       ⬆️    ⬆️ 
                比如i=5的时候sum=3 (0, 1)和(0, 3)的和为1, 所以前缀和是1的个数就有两个
                那么到i=5的时候和为2的个数为两个 即从(2, 5)和(4, 5)
             */
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            
            //将当前的和继续作为pre_sum存入map记录个数
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }
```



#### Leetcode 523

```java

class LC523 {
    /**
     * 如果[0, j]的和是rem [0, i]的和还是rem 并且i-j>1 那么[j,i]的和就是n * k
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, -1);
        
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            
            if (k != 0) {
                sum %= k;
            }
            
            if (preSum.containsKey(sum)) {
              	//如果 >0 反例 [0] k = 0,因为初始preSum.put(0, -1)
                if (i - preSum.get(sum) > 1) {
                    return true;
                }
            } else {
                preSum.put(sum, i);
            }

            //Wrong!!! [2, 0] k = 0
            //preSum.put(sum, i);
        }
        
        return false;
    }
}
```





### Union Find

---

#### Leetcode 1135(Connecting Cities With Minimum Cost)

```java
class Solution {
    public int minimumCost(int N, int[][] connections) {
        int[] root = new int[N];
        int count = N;
        int cost = 0;
        for (int i = 0; i < N; i++) {
            root[i] = i;
        }
        
        Arrays.sort(connections, (o1, o2) -> o1[2] - o2[2]);
        for (int[] conn : connections) {
            int rootx = find(root, conn[0] - 1);
            int rooty = find(root, conn[1] - 1);
            
            if (rootx != rooty) {
                root[rooty] = rootx;
                count -= 1;
                cost += conn[2];
            }
            
            if (count == 1) {
                break;
            }
            
        }
        
        return count == 1 ? cost : -1;
    }
    
    private int find(int[] root, int x) {
        if (root[x] != x) {
            root[x] = find(root, root[x]);
        }
        
        return root[x];
    }
}
```





### Trie

+ 每一个TrieNode都带有一个TrieNode数组类型的children元素，初始化的时候数组的每一个TrieNode都是null。当插入单词的时候将char放到对应的数组位置上, **比如 'c'就放到children[2]的位置, 但和一般存储不同点在于 不是将chidlren[2] = 'c' 而是-> children[c-'a] = new TrieNode(); 即当chilren[2] != null说明这个位置存储了一个字母**

+ **Leetcode**: **208, 211,212**

```java
//字典树的结构
class TrieNode {
  private TrieNode[] children;
  private String word;

  public TrieNode() {
  	children = new TrieNode[26];
  	word = "";
	}
}
```

```java
//字典树的插入方法
public void insert(String word) {
  	TrieNode node = root;

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);

      if (node.children[c - 'a'] == null) {
        node.children[c - 'a'] = new TrieNode();
      }

      node = node.children[c - 'a'];
    }

  	node.word = word;
}
```

```java
//字典树搜索
public boolean search(String word) {
    TrieNode node = root;

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (node.children[c - 'a'] == null) {
        return false;
      }
      node = node.children[c - 'a'];
    }
    return node.isWord;
}
```

```java
public boolean startsWith(String prefix) {
  	TrieNode node = root;
  
  	for (int i = 0; i < prefix.length(); i++) {
    	char c = prefix.charAt(i);
    	if (node.children[c - 'a'] == null) {
      	return false;
    	}
    	node = node.children[c - 'a'];
  	}

  	return true;
}
```



---



### Stack

---



### Two pointer

---

**木桶存水问题**

#### Leetcode 42

```java
class LC42 {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        trap(height);
    }
    public static int trap(int[] height) {
        int len = height.length;
        int maxl = height[0], maxr = height[len - 1];

        int l = 0, r = len - 1;
        int res = 0;
      	//最终一定是l,r停在最高的上面，因为谁矮移动谁，所以最后l==r
        while (l < r) {
          //每次移动高度较低的指针，因为较低的指针会影响水的高度，比如left = 1, right = 5, 即使right-- = 7也没有用，因为左边较低的还是1
            if (height[l] < height[r]) {
                res += Math.min(maxl, maxr) - height[l];
              	//注意这里是l先++到下一个位置，否则-height[l]可能为负数
                maxl = Math.max(maxl, height[++l]);
            } else {
                res += Math.min(maxl, maxr) - height[r];
                maxr = Math.max(maxr, height[--r]);    
            }
        }

        return res;
    }
}
```



### Sliding Window

#### Leetcode 3

Longest Substring Without Repeating Characters

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int[] ca = new int[256];
        
        int l = 0, r = 0;
        int max = 0;
        
        while (r < s.length()) {
            //每次先移动右指针，并将有指针走过的内容记录下来
            char cr = s.charAt(r++);
            ca[cr] += 1;
            
            //移动右指针以后判断是否满足题目的条件，比如出现重复的，总长度大于几个了，并调整左指针保证当前窗口内的内容满足条件
            while (ca[cr] > 1) {
                char cl = s.charAt(l);
                ca[cl]--;
                l++;
            }
            
            //统计结果，注意当题目条件为满足长度为某个数的时候，这里仍然需要加判断长度，否则会将小于该长度的值加进来
            max = Math.max(max, r - l);
        }
        
        return max;
    }
}
```



#### Leetcode 76(Minimum Window Substring)

```java
class LC76 {
    /**
     * 1. 先移动右指针让该窗口包含所有t中的字符串，每找到一个右边的字符，count--
     * 2. 当count == 0时说明t中的所有字符都找到了，开始移动左指针直到从窗口中移除掉一个t中的字符，然后再继续移动右指针寻找新的窗口
     * 3. 更新minL和minR的值找到最小窗口
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        
        int[] map_t = new int[256];
        
        int l = 0, r = 0;
        int minL = 0, minR = Integer.MAX_VALUE;
        
        int count = t.length();
        
        for (char c : t.toCharArray()) {
            map_t[c]++;
        }
        
        while (r < s.length()) {
            char rs = s.charAt(r++);

            if (--map_t[rs] >= 0) {
                count--;
            }
            
            while (count == 0) {
                char ls = s.charAt(l);
                if (++map_t[ls] > 0) {
                    count += 1;
                }
                
                if (minR - minL > r - l) {
                    minL = l;
                    minR = r;
                }
                
                l++;
            }
        }
        
        return minR == Integer.MAX_VALUE ? "" : s.substring(minL, minR);
    }
}
```





### Sorting

---

#### Leetcode 33

在一个被反转一次的有序数组中搜索target

```java
class LC33 {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        
        int l = 0, r = nums.length - 1;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (target == nums[mid]) {
                return mid;
            }
            
            //二分搜索只能在有序的数列中进行
            //先判断左边是否有序，如果有序并且target也在这个范围中，那么直接二分到左边区域，否则搜索右侧
            //注意这里nums[l]<=nums[mid]因为当有两个元素的时候l == mid
            if (nums[l] <= nums[mid]) {
                if (nums[mid] > target && nums[l] <= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else { //如果右边是否有序，如果有序并且target也在这个范围中，那么直接二分到右边区域
                if (nums[mid] < target && nums[r] >= target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }           
        }
        return -1;
    }
}
```

***错误做法***：先判断二分 后判断边界

因为即使nums[mid] < target并且nums[r] < target也不能确定target就在左侧，因为右侧可能是无序的，所以**nums[r]不一定是最大的元素**

```java
public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            if (nums[mid] > target) {
                if (nums[l] > target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                if (nums[r] < target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        
        return -1;
    }
```



#### Leetcode 31(Next Permutation)

```java
class LC31 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        
        int n = nums.length;
        int asIndex = n - 2;
        
        //Step1: 从最后一个开始 找到第一个上升序列的位置，如果整个数组都是下降顺序，则只需要将其整体reverse就行了
        //[3,5,4,2,1]找到第一个上升的index asIndex = 1即[3]的位置
        while (asIndex >= 0 && nums[asIndex] >= nums[asIndex + 1]) {
            asIndex--;
        }
        
        //Step2: asIndex >= 0表示存在一个上升的位置，此时将这个元素与后半部分的下降序列的第一个比它大的数字交换，这样保证最终找到的是比当前数字大的最小的那个数字
        //[3,5,4,2,1]最小的比当前元素大的是[4]，将这两个元素交换 变成[4,5,3,2,1]
        if (asIndex >= 0) {
            int firstLargerIndex = n - 1;
            while (firstLargerIndex > 0 && nums[firstLargerIndex] <= nums[asIndex]) {
                firstLargerIndex -= 1;
            }

            swap(nums, asIndex, firstLargerIndex);
        }
        

        //Step3: 将整个有半部分的值按照从小到大排列 保证最终有半部分是最小的一个值
        //[4,1,2,3,5]
        reverseSort(nums, asIndex + 1, n - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverseSort(int nums[], int i, int j) {
        if (i >= j) return;
        
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
```





### BinarySearch

+ **Template**

  ```java
  /**
  * 搜索区间[l, r)
  */
  private int binarySearch(int l, int r) {
    	while (l < r) {
        	int m = l + (r - l) / 2;
        	if (g(m)) {
              r = m; //新的搜索区间[l, m)
          } else {
            	l = m + 1; //新的搜索区间[m+1, r)
          }
      }
    
    	return l; //l表示找到的第一个满足g(m) = true的值
  }
  ```

  

+ Sample: 找到第一个大于或者大于等于目标的模板

  ```java
  class BinarySearchTest {
      public static void main(String[] args) {
          // int[] test1 = {1,3,5,7,9};
          int[] test2 = {1,3,3,3,3};
  
          int res = binarySearch(test2, 3);
          System.out.println(res);
      }
  
      public static int binarySearch(int[] array, int target) {
          int l = 0, r = array.length - 1;
  
          while (l <= r) {
              int mid = l + (r - l) / 2;
  
              //1. >=: 返回第一个大于等于目标的值
              //2. >: 返回第一个大于目标的值
            	//返回第一个g(m) == true的值
              if (array[mid] > target) {
                  //只要中点值大于目标值就在中点的左区间找，因为l=mid+1,所以最终跳出循环的时候正好是第一个大于目标的值
                  r = mid - 1;
              } else {
                  l = mid + 1;
              }
          }
  
          return l;
      }
  }
  ```



#### Leetcode 34

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;
        
        int l = 0, r = nums.length - 1;
        
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
        
            if (nums[mid] > target) {
                r = mid - 1;
            } else if(nums[mid] < target) {
                l = mid + 1;
            } else {
                int start = mid - 1, end = mid + 1;
                
                while (start >= 0 && nums[mid] == nums[start]) {
                    start--;
                }
                    
                while (end < nums.length && nums[mid] == nums[end]) {
                    end++;
                }
                
                res[0] = start + 1;
                res[1] = end - 1;
                return res;
            }
        }
        
        return res;
    }
}
```



#### Leetcode 278

```java
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            
          	//左闭右开区间，当找到一个返回true的时候舍掉这个，继续往左边找
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return start;
    }
}
```



#### Leetcode 69. Sqrt(x)

```java
class Solution {
	  /**
     * 这道题是找上界，所以可以找到 >根号值的下界，然后再减去1
     */
    public int mySqrt(int x) {
        int lo = 1, hi = x;

        if (x < 2) return x;
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (mid > x / mid) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo - 1;
    }
}
```





### PriorityQueue

#### Leetcode 253

Meeting room

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            //每次判断堆顶最小的结束时间和当前的开始时间
            //1.如果堆顶最小结束时间<当前开始时间，那么不能共用同一个room，所以将当前结束时间加入堆
            //2.如果堆顶最小结束时间>=当前开始时间，则可以共用一个room，只需要更新当前room的结束时间即可
            if (intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            
            pq.offer(intervals[i][1]);
        }
        
        return pq.size();
    }
}
```





### Greedy

#### Leetcode 612

```java
class LC621 {
    public int leastInterval(char[] tasks, int n) {
        //先按照字母个数进行排序 个数最多的在最后
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        
        Arrays.sort(map);

        //找到个数最多的字母 然后减1 为了找到需要多少个空档填cool down 最后一段是不需要空档的
        int maxVal = map[25] - 1;
        //先假设这些空档全填上idle
        int idleSlots = maxVal * n;
        
        //从个数第二大的开始填这些空位置 用之前假设的idle个数减去能够填的字母个数
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            //因为maxVal是出现最多的次数-1, 如果存在和其个数一样的字母个数，应该取小的
            //比如A=3 B=3 n=2 那么如果先把A固定, 最后的B放到最后的A后面就可以 并不消耗idle
            idleSlots -= Math.min(map[i], maxVal); 
        }
        
        //最后的结果就是剩余没被字母填满的idle个数 + 整个task的长度
        return idleSlots > 0 ? tasks.length + idleSlots : tasks.length;
    }
}
```





### LRU/LFU Cache

---

1. LRU是**淘汰最后**被访问的元素, 而LFU是**淘汰访问次数最少**的元素

2. Least recently used in O(1)

   > 思路：
   >
   > 1. 维护一个map和双向链表, map用于记录index，双向链表用于调整节点
   >
   > 2. 每次调用get或者put(key已经存在的情况)的时候都将当前key的节点移动到链表头部，这样最近没有被使用的key的节点就沉到了链表的最后
   > 3. 当容量满的时候就从链表最后pop掉一个节点并且将其从map中删除
   > 4. 每次新加入的节点加入到链表头部，这个和LFU不同，不需要考虑调用次数，即使当容量满的时候，也要让新加的节点先进入，只要是新加入的节点就在链表最前面。

   用一个map记录index和ListNode

   ```java
   class ListNode {
       int key;//需要有key,map.remove(key)的时候用
       int val;
       ListNode next;
       ListNode pre;
   
       public ListNode(int k, int v) {
         this.key = k;
         this.val = v;
         next = null;
         pre = null;
       }
   }
   private ListNode head, tail;
   private Map<Integer, ListNode> map;
   ```

   ```java
   public int get(int key) {
       if (!map.containsKey(key)) {
         return -1;
       }
   
       ListNode node = map.get(key);
       moveToHead(node);
   
       return node.val;
   }
   
   public void put(int key, int value) {
       if (map.containsKey(key)) {
         ListNode node = map.get(key);
         node.val = value;
   
         map.put(key, node);
         moveToHead(node);
         return;
       }
   
       if (capacity == 0) {
         ListNode last = popTail();
         map.remove(last.key);
   
         capacity += 1;
       } 
   
       capacity -= 1;
       ListNode node = new ListNode(key, value);
       map.put(key, node);
   
       addFront(node);
   }
   ```

   

### Parenthesis

#### Leetcode 1249

```java
class LC1249 {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) return s;
        
        int open = 0;
        StringBuilder sb = new StringBuilder();
        
        //先移除非法的右括号
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open += 1;
            } else if (c == ')') {
                if (open == 0) {
                    continue;
                }
                open -= 1;
            }
            sb.append(c);
        }

        StringBuilder res = new StringBuilder();
        
        //从后往前移除多余的左括号
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '(' && open-- > 0) {
                continue;
            }
            res.append(sb.charAt(i));
        }
        
        return res.reverse().toString();
    }
}
```

