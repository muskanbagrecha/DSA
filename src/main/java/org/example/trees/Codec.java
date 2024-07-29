package org.example.trees;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.remove();
            if (curr == null) {
                sb.append("#,");
                continue;
            }

            sb.append(curr.data).append(",");
            q.add(curr.left);
            q.add(curr.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodelist = data.split(",");
        String rootNode = nodelist[0];
        if(rootNode.equals("")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(rootNode));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while(!q.isEmpty()){
            TreeNode curr = q.remove();
            if(i<nodelist.length && !nodelist[i].equals("#")){
                curr.left = new TreeNode(Integer.valueOf(nodelist[i]));
                q.add(curr.left);
            }
            i++;
            if(i<nodelist.length && !nodelist[i].equals("#")){
                curr.right = new TreeNode(Integer.valueOf(nodelist[i]));
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }
}