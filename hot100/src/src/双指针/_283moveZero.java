package 双指针;

public class _283moveZero {
    public void moveZeroes(int[] nums) {
        int count = nums.length;
        int left = 0;
        int right = 0 ;
        for (int i = 0; i < count; i++) {
            if(nums[right] != 0){
                swap(nums, left, right);
                left++;
            }
            right++;
        }

    }

    public void moveZeroes2(int[] nums) {
        int count = nums.length;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count-i; j++) {
                if(nums[j] == 0 && j+1 < count){
                    swap(nums, j, j+1);
                }

            }

        }

    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


}
