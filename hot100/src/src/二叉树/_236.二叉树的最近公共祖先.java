import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return posOrder(root, p, q);
    }

    public TreeNode posOrder(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }
        TreeNode ans1 = posOrder(root.left, p, q);
        TreeNode ans2 = posOrder(root.right, p, q);
        if (ans1 != null && ans2 != null) {
            return root;
        } else {
            return ans1 != null ? ans1 : ans2;
        }
    }
}