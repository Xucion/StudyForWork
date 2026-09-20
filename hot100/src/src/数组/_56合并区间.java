package 数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _56合并区间 {
    class Solution {
        public int[][] merge(int[][] intervals) {
            List<int[]> res = new ArrayList<>();
            Arrays.sort(intervals, new Comparator<int[]>(){
                public int compare(int[] interval1, int[] interval2){
                    return Integer.compare(interval1[0], interval2[0]);
                }
            });

            int start = intervals[0][0];//最小左边界
            int rightmostRightBound = intervals[0][1];//上一个区间的右区间
            for(int i = 1; i< intervals.length; i++){
                //如果这个区间的左边界大于上个区间的右边界
                if(intervals[i][0]>rightmostRightBound) {
                    res.add(new int[]{start, rightmostRightBound});//添加区间
                    start = intervals[i][0];//重新生成左右区间
                    rightmostRightBound = intervals[i][1];
                } else{
                    rightmostRightBound = Math.max(rightmostRightBound, intervals[i][1]);//合并区间
                }
            }
            res.add(new int[]{start,rightmostRightBound});
            return res.toArray(new int[res.size()][]);
        }
    }
}
