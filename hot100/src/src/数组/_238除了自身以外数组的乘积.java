package 数组;

public class _238除了自身以外数组的乘积 {
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int ans[] = new int[n];
            ans[0] = 1;
            int right = 1;

            for(int i = 1; i<n; i++){
                ans[i] = ans[i-1] * nums[i-1];//ans[i]存的是前i项的和
            }

            ans[n-1] = right * ans[n-1];

            for(int i = n-2; i >= 0;i--){
                right = right * nums[i+1];//right存的是第i项到最后的和
                ans[i] = right * ans[i];
            }
            return ans;
        }
    }
}
