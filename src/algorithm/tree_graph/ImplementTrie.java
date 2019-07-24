package algorithm.tree_graph;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-06 17:02
 * @Description: implement a trie with insert, search, and startsWith methods.
 * 实现一个字典树
 * leetcode 208
 */

class TrieNode{
    char val;
    boolean isWord = false;
    TrieNode[] children = new TrieNode[26];
    public TrieNode(){
    }
    public TrieNode(char c){
        this.val = c;
    }

}

class Trie {
    //根节点
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode(' ');
    }

    public TrieNode getRoot() {
        return root;
    }

    public void setRoot(TrieNode root) {
        this.root = root;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        //遍历字符串
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            //如果当前节点对应位置的儿子还没有添加，则新添加一个TrieNode
            if (curr.children[ch - 'a'] == null){
                curr.children[ch - 'a'] = new TrieNode(ch);
            }
            //继续迭代当前节点
            curr = curr.children[ch - 'a'];
        }
        curr.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) return false;
            curr = curr.children[ch - 'a'];
        }
        return curr.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (curr.children[ch - 'a'] == null) return false;
            curr = curr.children[ch - 'a'];
        }
        return true;
    }
}


public class ImplementTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("mahaonan");
        System.out.println(trie.startsWith("mahaon"));
    }
}
