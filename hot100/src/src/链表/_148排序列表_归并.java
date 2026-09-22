package 链表;

public class _148排序列表_归并 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    class Solution {

        public ListNode sortList(ListNode head) {

            //递归结束条件：空链表或者长度为1
            if (head == null || head.next == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            //从中间切成两个链表
            ListNode right = slow.next;
            slow.next = null;
            ListNode left = head;
            //左边的链表递归排序
            left = sortList(left);
            //右边的链表递归排序
            right = sortList(right);
            //合并两个有序列表
            return mergeList(left, right);
        }

        ListNode mergeList(ListNode first, ListNode second){
            ListNode dummyNode = new ListNode();
            ListNode current = dummyNode;
            // 合并操作
            while(first != null && second != null){
                if(first.val < second.val){
                    current.next = first;
                    first = first.next;
                }else{
                    current.next = second;
                    second = second.next;
                }
                current = current.next;
            }
            if(first == null){
                current.next = second;
            }else{
                current.next = first;
            }
            return dummyNode.next;
        }
    }
}
