package 双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15三数之和 {//重点Arrays.asList(nums[i],nums[L],nums[R])
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3)
            return ans;
        Arrays.sort(nums);
        for(int i = 0; i < len; i++){
            if(nums[i] > 0) //如果第一个就是正数
                break;
            if(i > 0 && nums[i]==nums[i-1]) //如果这个数和前一个数相同，说明已经计算过了，直接跳过
                continue;
            int L = i + 1;//左指针，指向第一个数的下一个数
            int R = len - 1;//右指针，指向最后一个数
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1])//如果左指针这个数和下一个数相同，直接跳过
                        L++; // 去重
                    while (L<R && nums[R] == nums[R-1])//如果右指针这个数和下一个数相同，直接跳过
                        R--; // 去重
                    L++;//再次移动
                    R--;
                }
                else if(sum < 0)
                    L++;
                else if(sum > 0)
                    R--;
            }
        }
        return ans;
    }
}
