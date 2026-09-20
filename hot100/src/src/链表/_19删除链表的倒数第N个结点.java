package 链表;

public class _19删除链表的倒数第N个结点 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode dummyNode = new ListNode();
            dummyNode.next = head;
            ListNode curr = dummyNode;

            for(int i = 0; i < n; i++){//快指针，先走n步
                head = head.next;
            }
            while(head != null){//慢指针和快指针一起走，直到快指针走到头
                head = head.next;
                curr = curr.next;
            }
            //此时慢指针的位置就是要删除位置的前一个位置
            curr.next = curr.next.next;

            return dummyNode.next;

        }
    }
}
