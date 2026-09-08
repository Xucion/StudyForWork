package 哈希;

import java.util.HashMap;
//注意使用哈希来代替遍历
public class _1两数之和 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();//diff,diff的下标
        for (int i = 0; i < nums.length; i++) {
            int choose = nums[i];
            int diff = target - choose;
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
            map.put(choose, i);
        }
        return null;
    }
}
