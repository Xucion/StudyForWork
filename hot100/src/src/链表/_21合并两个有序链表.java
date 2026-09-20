package 链表;

public class _21合并两个有序链表 {

      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode head = new ListNode();//head是一个头
            ListNode cur = head;
            while(list1 !=null && list2 != null){//直到有一个链表遍历完
                if(list1.val>=list2.val){
                    cur.next = list2;
                    list2 = list2.next;
                }else{
                    cur.next = list1;
                    list1 = list1.next;
                }
                cur = cur.next;
            }

            // 把剩下的链表直接接上（因为已有序）
            if (list1 != null) {
                cur.next = list1;
            }

            if (list2 != null) {
                cur.next = list2;
            }

            return head.next;
        }
    }
}
