package 链表;

import java.util.ArrayList;
import java.util.List;

public class _234回文链表 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();//创建列表
        ListNode currentNode = head;
        while (currentNode != null){//把值复制到列表里
            vals.add(currentNode.val);
            currentNode=currentNode.next;
        }
        int left =0;
        int right = vals.size()-1;
        while(left<right){
            if(!vals.get(left).equals(vals.get(right))){//双指针比较是否相等
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
