package 滑动窗口;

import java.util.HashSet;
import java.util.Set;

public class _3无重复字符的最长字串 {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            char[] ss = s.toCharArray();
            //哈希集合，记录每个字符是否出现过
            Set<Character> set = new HashSet<Character>();
            int res = 0;
            for(int left = 0, right = 0; right < s.length(); right++) {
                char ch = ss[right];
                while(set.contains(ch)) {//一直移除到没有重复
                    set.remove(ss[left]);
                    left++;
                }
                set.add(ss[right]);
                res = Math.max(res, right - left + 1);
            }
            return res;
            //
        }
    }

}
