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
    public int ret;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        traversal(root, (long) targetSum - root.val);
        pathSum(root.left, targetSum); // 左子树里任意起点的路径数
        pathSum(root.right, targetSum);
        return ret;
    }

    public void traversal(TreeNode root, long targetSum) {
        if (targetSum == 0) {
            ret++;
        }

        if (root.left != null) {
            traversal(root.left, targetSum - root.left.val);
        }
        if (root.right != null) {
            traversal(root.right, targetSum - root.right.val);
        }
        return;
    }
}