package 滑动窗口;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _438找到字符串中所有字母异位词 {
    public List<Integer> findAnagrams(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        if(slen < plen){
            return new ArrayList<Integer>();
        }
        List<Integer> ans = new ArrayList<Integer>();
        int[] scount = new int[26];
        int[] pcount = new int[26];
        for(int i = 0; i < plen; i++){
            ++pcount[p.charAt(i) - 'a'];
        }
        for(int i = 0; i < slen; i++){
            ++scount[s.charAt(i) - 'a'];
            if(i > plen-1){//索引的长度>plen-1，表示如果窗口的长度大于 p 的长度
                --scount[s.charAt(i - plen)-'a'];//则要缩减窗口
            }
            if(i >= plen-1 && Arrays.equals(pcount, scount)){//索引的长度 >= plen-1，表示窗口长度至少为 plen，可以开始检查了
                ans.add(i-plen+1);
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams超时(String s, String p) {
        int plen = p.length();
        int slen = s.length();
        if (slen < plen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        char[] pchar = p.toCharArray();
        char[] winchar;
        Arrays.sort(pchar);
        for(int i=0; i <= slen - plen; i++)
        {
            winchar = s.substring(i,i+plen).toCharArray();
            Arrays.sort(winchar);
            if(Arrays.equals(winchar,pchar))
            {
                ans.add(i);
            }
        }
        return ans;
    }
}
