/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null ||preorder.length == 0){
            return null;
        }
        int index = 0;
        TreeNode root = new TreeNode(preorder[0]);
        for(; index<inorder.length; index++){
            if(inorder[index]==preorder[0]){
                break;
            }
        }
        int[] leftPre = Arrays.copyOfRange(preorder,1,index+1);
        int[] rightPre = Arrays.copyOfRange(preorder,index+1,preorder.length);
        int[] leftIn = Arrays.copyOfRange(inorder,0,index);
        int[] rightIn = Arrays.copyOfRange(inorder,index+1,inorder.length);

        root.left = buildTree(leftPre,leftIn);
        root.right = buildTree(rightPre,rightIn);
        return root;
        
    }
}