package 哈希;

import java.util.HashSet;
//通过分解目标结构的规则来实现分步查找
public class _128最长连续序列 {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums){
            set.add(n);
        }
        int count = 0;
        int res = 0;
        for (int n : set) {
            if(! set.contains(n -1))
            {
                //是头
                count = 1;
                int curr = n;
                while (set.contains (curr + 1)){
                    //是中
                    count++;
                    curr++;
                }
                //结束不满足条件，是尾
                res = Math.max(res, count);
            }
        }
        return res;
    }
}
