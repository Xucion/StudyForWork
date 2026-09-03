package 双指针;

public class _42接雨水 {
    public int trap(int[] height) {
        int n = height.length;
        if (n==0){
            return 0;
        }

        int[] left = new int[n], right = new int[n];

        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(height[i], left[i-1]);
        }

        right[n-1] = height[n-1];
        for (int i = n-2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i+1]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }
        return res;
    }
}
