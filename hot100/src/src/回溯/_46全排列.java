package src.回溯;

import java.util.ArrayList;
import java.util.List;

public class _46全排列 {
    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used;

        public List<List<Integer>> permute(int[] nums) {
            if (nums.length == 0) {
                return result;
            }
            used = new boolean[nums.length];
            permuteHelper(nums);
            return result;
        }

        private void permuteHelper(int[] nums) {
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                used[i] = true;
                path.add(nums[i]);
                permuteHelper(nums);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }
}
