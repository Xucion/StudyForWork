package 链表;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _138随机链表的复制 {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    class Solution {
        public Node copyRandomList(Node head) {
            Map<Node, Node> map = new HashMap<>();
            if(head == null){
                return null;
            }
            // 第一遍：创建所有新节点
            Node curr = head;
            while(curr != null){
                map.put(curr, new Node(curr.val));
                curr = curr.next;
            }
            // 第二遍：设置 next 和 random
            curr = head;
            while(curr!=null){
                Node copy = map.get(curr);
                copy.next = map.get(curr.next);
                copy.random = map.get(curr.random);
                curr = curr.next;
            }
            return map.get(head);
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        System.out.println(map.entrySet());
        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        Set<Integer> s = new HashSet<>();
        s.add(1);
        String a = "Hello";

    }
}
