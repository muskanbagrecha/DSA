package org.example.trie;

public class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!temp.containsKey(c)) {
                temp.put(c, new TrieNode());
            }
            temp = temp.getKey(c);
        }
        temp.flag = true;
    }

    public boolean search(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!temp.containsKey(c)) {
                return false;
            }
            temp = temp.getKey(c);
        }
        return temp.isStringEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!temp.containsKey(c)) {
                return false;
            }
            temp = temp.getKey(c);
        }
        return true;
    }

}

class TrieNode {
    TrieNode[] links;
    boolean flag;

    public TrieNode() {
        links = new TrieNode[26];
        flag = false;
    }

    public TrieNode getKey(char c) {
        return links[c - 'a'];
    }

    public boolean containsKey(char c) {
        return links[c - 'a'] != null;
    }

    public void put(char c, TrieNode trieNode) {
        links[c - 'a'] = trieNode;
    }

    public boolean isStringEnd() {
        return this.flag;
    }
}