package 链表;

public class _24两两交换链表中的节点 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode curr = head;
        ListNode pre = dummyNode;
        if (head == null || head.next == null) {
            return head;
        }
        //head快 curr慢
        head = head.next;
        while (head != null) {
            curr.next = head.next;//交换节点
            head.next = curr;
            pre.next = head;//别忘了把前面的节点接到后面
            if (curr.next != null && curr.next.next != null) {
                ListNode temp = curr;
                curr = head;//确保curr在前，head在后
                head = temp;
                curr = curr.next.next;
                head = head.next.next;
                pre = pre.next.next;
            } else {
                break;
            }
        }
        return dummyNode.next;

    }

    public static void main(String[] args) {
        //定义链表【1->2->3->4】
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        //调用swapPairs
        ListNode newHead = swapPairs(head);

        //打印链表
        ListNode curr = newHead;
        while (curr != null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
    }
}
