package src.图论;

public class _208实现Trie {
    class TrieNode {
        public TrieNode[] next = new TrieNode[26];// 一共26个位置，对应26个字符
        boolean isEnd = false;
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // 插入方法
        public void insert(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {// 遍历每个字符
                int idx = word.charAt(i) - 'a';// 在该祖父对应的位置插入节点。
                if (curr.next[idx] == null) {
                    curr.next[idx] = new TrieNode();// 只有为空时才创建节点
                }
                curr = curr.next[idx];// 继续往下一个字符的位置走。
            }
            curr.isEnd = true;// 终止符
        }

        public boolean search(String word) {
            TrieNode curr = find(word);
            if (curr != null && curr.isEnd == true) {
                return true;
            } else {
                return false;
            }
        }

        public boolean startsWith(String prefix) {
            TrieNode curr = find(prefix);
            if (curr != null) {
                return true;
            } else {
                return false;
            }
        }

        // 辅助方法：沿着字符串走，走不通返回null
        public TrieNode find(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (curr.next[idx] == null) {
                    return null;
                } else {
                    curr = curr.next[idx];
                }
            }
            return curr;
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
