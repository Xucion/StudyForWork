package 链表;

public class _147对链表进行插入排序 {
    public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    class Solution {
        public ListNode insertionSortList(ListNode head) {
            if (head == null && head.next ==null){
                return head;
            }
            // 哑节点
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;
            //pre负责指向新元素，last负责指向新元素的前一元素
            //判断是否需要执行插入操作
            ListNode pre = head.next;
            ListNode last = head;
            ListNode temphead = dummyNode;
            while(pre != null){
                if (last.val <= pre.val) {
                    pre = pre.next;
                    last = last.next;
                    continue;
                }
                // 开始出发，查找新元素的合适位置
                temphead = dummyNode;
                while (temphead.next.val <= pre.val) {
                    temphead = temphead.next;
                }
                // 此时我们已经找到了合适位置，需要进行插入
                last.next = pre.next; //先解去 指向当前节点的箭头，使得前面的节点指向当前节点的下一个节点
                pre.next = temphead.next; //再改变 当前节点箭头的指向，使得当前节点箭头的指向 指向 目标节点
                temphead.next = pre; //再连接 指向当前节点的箭头
                pre = last.next;
            }
            return dummyNode.next;
        }
    }
}
