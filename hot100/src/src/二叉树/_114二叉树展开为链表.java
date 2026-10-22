import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    List<TreeNode> ans = new ArrayList<TreeNode>();

    public void flatten(TreeNode root) {// 先中序，再尾插法
        if (root == null) {
            return;
        }
        preOrder(root);
        TreeNode tail = root;// 因为这题返回值是void，所以尾插法不需要头节点，只需要尾节点
        for (int i = 1; i < ans.size(); i++) {
            tail.right = ans.get(i);
            tail.left = null;
            tail = ans.get(i);
        }
        tail.left = null;
        tail.right = null;
    }

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        ans.add(root);
        preOrder(root.left);
        preOrder(root.right);
    }
}