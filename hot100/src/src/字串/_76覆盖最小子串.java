package 字串;

import java.util.HashMap;
import java.util.Map;

public class _76覆盖最小子串 {
    class Solution {
        public String minWindow(String s, String t) {
            if(s.length()==0||t.length()==0)
                return "";

            Map<Character, Integer> mapT = new HashMap<>();//统计t中字符出现的次数
            Map<Character, Integer> mapS = new HashMap<>();//统计s中字符出现的次数
            for(int i=0;i<t.length();i++){
                mapT.put(t.charAt(i),mapT.getOrDefault(t.charAt(i),0)+1);
            }
            int tCount = mapT.size();//需满足的字符个数（指完全满足，包括值和数量）
            int have = 0;//满足的字符个数
            int resStart = 0; int resLength = Integer.MAX_VALUE;//子串的起始位置和长度
            int left = 0; int i = 0;//当前字串的左右边界
            while(i<s.length()){
                if(mapT.containsKey(s.charAt(i))){//若t包含该字符
                    mapS.put(s.charAt(i),mapS.getOrDefault(s.charAt(i),0)+1);//放入s中统计
                    if(mapS.get(s.charAt(i)).equals(mapT.get(s.charAt(i)))){//若该字符出现的次数在s和t中一致，则满足字符的个数+1
                        have++;
                    }
                }
                while(have == tCount){//如果已经完全满足
                    if (i - left + 1 < resLength) {//判断长度是否更小，若更小则更新
                        resLength = i - left + 1;
                        resStart = left;
                    }
                    if(mapT.containsKey(s.charAt(left))){//判断左边界是否包含该字符（因为需要收缩左边界）
                        mapS.put(s.charAt(left),mapS.get(s.charAt(left))-1);//若包含，则减少该字符出现的次数
                        if(mapS.get(s.charAt(left))<mapT.get(s.charAt(left))){//如果收缩左边界导致该字符出现的次数不满足t中的次数。
                            have--;//满足的字符个数-1
                        }
                    }
                    left++;//收缩左边界
                }
                i++;//延申右边界
            }
            if(resLength == Integer.MAX_VALUE){
                return "";
            }
            else
                return s.substring(resStart, resStart + resLength);
        }
    }
}
