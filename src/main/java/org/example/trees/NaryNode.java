package org.example.trees;

import java.util.List;

public class NaryNode {
    public int data;
    public List<NaryNode> children;

    public NaryNode() {
    }

    public NaryNode(int data) {
        data = data;
    }

    public NaryNode(int data, List<NaryNode> _children) {
        data = data;
        children = _children;
    }

}
