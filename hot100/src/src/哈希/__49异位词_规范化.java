package 哈希;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//借助对数据的重新标准化来制定哈希，从而聚合数据
public class __49异位词_规范化 {
    public List<List<String>> groupAnagram(String[] strs){
        HashMap<String, List<String>> map = new HashMap<>();
        List<String> value;
        for (String key: strs){
            char[] chars = key.toCharArray();
            Arrays.sort(chars);
            String sortKey = new String(chars);
            if (map.containsKey(sortKey)){
                map.get(sortKey).add(key);
            } else {
                List<String> s = new ArrayList<>();
                s.add(key);
                map.put(sortKey, s);
            }
        }
        return new ArrayList<>(map.values());
    }
}
