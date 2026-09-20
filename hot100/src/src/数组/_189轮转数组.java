package 数组;

public class _189轮转数组 {
    class Solution {
        public void rotate(int[] nums, int k) {//新的数组，使用arraycopy(源数组，下标，目标数组，下标，n个元素)
            int n = nums.length;
            int[] newArr = new int[n];
            for(int i =0; i < n; i++){
                newArr[(i+k)%n] = nums[i];
            }
            System.arraycopy(newArr,0,nums,0,n);
        }
    }
}
