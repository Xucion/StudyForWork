package 数组;

public class _41缺失的第一个正数 {
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for(int i = 0; i < n; i++){
                if(nums[i]<=0) {
                    nums[i] = n + 1;
                }
            }
            for (int i=0; i<n; i++){
                int num = Math.abs(nums[i]);//取数组中数的绝对值
                if(num<=n){
                    nums[num - 1] = -Math.abs(nums[num-1]);//假设数组中的值为1，则把nums[0]置负
                }
            }
            for(int i= 0;i<n;i++){
                if(nums[i]>0){//遍历，如果nums[i]的位置不是负数，说明i+1没有出现过
                    return i+1;
                }
            }
            return n+1;
        }
    }
}
