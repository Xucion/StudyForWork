package 双指针;

public class _42接雨水 {
    public int trap(int[] height) {
        int n = height.length;
        if(n == 0){
            return 0;
        }
        /*
        * left[i]：位置i左边（包含自己）的最高柱子
        * right[i]：位置i右边（包含自己）的最高柱子
        * */
        int[] left = new int[n];
        int[] right = new int[n];

        /*
        * 计算每个位置左边的最高柱子
        * left[i]要么是当前位置的高度，要么是之前位置的最高柱子
        * */
        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(height[i],left[i-1]);
        }
        /*
        * 计算每个位置右边的最高柱子
        * right[i]要么是当前柱子，要么是之前位置的最高柱子
        * */
        right[n-1] = height[n-1];
        for(int i = n-2; i>=0;i--){
            right[i]=Math.max(height[i],right[i+1]);
        }
        /*
        * 对于位置i
        * 能接水的高度由左右两边较矮的最高柱子决定
        *
        * 水面高度：
        * min（left[i],right[i]）
        *
        * 当前柱子上方的水量：
        * min（left[i],right[i]）-height[i]
        * */
        int res =0;
        for(int i=0;i<n;i++){
            res += Math.min(left[i],right[i]) -height[i];
        }
        return res;
    }
}
