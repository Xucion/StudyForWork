package 字串;

import java.util.HashMap;
import java.util.Map;

public class _560和为k的子数组 {
    public class Solution {
        public int subarraySum(int[] nums, int k) {
            Map<Integer, Integer> prefixSumToCount= new HashMap<>();//key是前缀和，value是出现的次数
            prefixSumToCount.put(0,1);//0是前0项的前缀和，出现1次
            int prefixSum = 0;
            int count = 0;
            for(int i =0; i<nums.length;i++){
                prefixSum += nums[i];//前i项的和

                int target = prefixSum - k; //(prefixSum - target = k)//目标值（前i项-前j项=k），target是前j项的和
                if(prefixSumToCount.containsKey(target)){
                    count += prefixSumToCount.get(target);
                }
                prefixSumToCount.put(prefixSum,prefixSumToCount.getOrDefault(prefixSum, 0) + 1);
            }
            return count;

        }
    }

// public class Solution {//O(n^2)
//     public int subarraySum(int[] nums, int k) {
//         int count = 0;
//         for (int start = 0; start<nums.length;start++){
//             int sum = 0;
//             for(int end = start;end<nums.length;end++){
//                 sum+=nums[end];
//                 if(sum == k){
//                     count++;
//                     //break;不能break，因为可能有负数
//                 }
//             }
//         }
//         return count;
//     }
// }

}
