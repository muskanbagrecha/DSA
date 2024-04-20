package org.example.trees;

import java.util.*;

public class BinaryTreeProblems {

    //    https://www.geeksforgeeks.org/problems/height-of-binary-tree/1

    //This is same as finding max depth of a tree.

    public static int height(TreeNode node) {
        // code here
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // We perform DFS as we need to reach max depth.

    // Time: O(n) - where n is the total no of nodes
    // Space: O(n) - recursion stack
    // In the worst case (e.g., a skewed tree), the recursion stack could have n function calls, leading to a space complexity of O(n).
    // For a balanced tree, the space complexity would be O(log n) due to the height of the tree, but the worst-case is considered O(n).

    //Traversals

    //Pre-Order Traversal
    //root-left-right
    public void preOrderTraversal(TreeNode root) {
        if (root == null) {
            System.out.println("END");
            return;
        }
        System.out.println(root.data + " -> ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
    //O(n) time and O(n) due to stack


    //Iterative
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                ans.add(node.data);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return ans;
    }
    //O(n) time and O(n) due to stack

    public List<Integer> preorderNAryTree(NaryNode root) {
        List<Integer> list = new ArrayList<>();
        traverse(root, list);
        return list;
    }

    public void traverse(NaryNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.data);
        for (NaryNode child : root.children) {
            traverse(child, list);
        }
    }

    public List<Integer> preorderNaryIterative(NaryNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<NaryNode> s = new Stack<>();
        if (root == null) {
            return list;
        }
        s.add(root);
        while (!s.isEmpty()) {
            NaryNode node = s.pop();
            list.add(node.data);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                s.push(node.children.get(i));
            }
        }
        return list;
    }

    public void inOrderTraversal(TreeNode root) {
        if (root == null) {
            System.out.println("END");
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.data + " -> ");
        inOrderTraversal(root.right);
    }
    //O(n) time and O(n) due to stack - in iterative we can do in O(1) time.

    //Iterative:
    //Approach 1:
    //Algo:
    //1. Add the right child, the root, the left child
    //2. This algo adds null entries in the stack too. So if top is null, we will pop the null as after that we will have our lead node and add that lead node to the list
    //3.  We also have to check edge case of empty list before adding to list as for the last node, the last thing in the stack will be null.
    //4. If top is not null, we pop the top (which will be the root for that node, and push it's right child (existent or not), push the node itself and then the left.
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        if (root == null) {
            return list;
        }
        s.push(root.right);
        s.push(root);
        s.push(root.left);
        while (!s.isEmpty()) {
            TreeNode top = s.peek();
            if (top != null) {
                s.pop();
                s.push(top.right);
                s.push(top);
                s.push(top.left);
            } else {
                s.pop();
                if (s.isEmpty()) {
                    return list;
                }
                list.add(s.pop().data);
            }
        }
        return list;
    }

    //Time: O(n) - each node is processed once
    //Space: O(n) - O(logn) for balanced trees, O(n) for skewed trees
    //However, we are taking up extra space for null entries.

    //Approach 2:
//    Start with the root node and explore as far left as possible, pushing each node onto the stack.
//    Once you reach a node with no left child, process that node by popping it from the stack and adding its value to the result list.
//    After processing a node, move to its right child and repeat the process of going as far left as possible from that node.
//    Continue this pattern until you have processed all nodes. The stack's nature ensures that nodes are processed in the correct order (left, root, right).
//    Finish when the stack is empty, indicating that all nodes have been visited in the correct order.

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode current = root;
        while (current != null || !s.isEmpty()) {
            while (current != null) {
                s.push(current);
                current = current.left;
            }
            current = s.pop();
            list.add(current.data);
            current = current.right;
        }
        return list;
    }

    //Time & space: same as above but stack does not need to store unnecessary null entries so there is a slight optimization.

    public void postOrderTraversal(TreeNode root) {
        if (root == null) {
            System.out.println("END");
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.data + " -> ");
    }
    //O(n) time and O(n) due to stack - in iterative we can do in O(1) time.

    //https://leetcode.com/problems/binary-tree-level-order-traversal
    //Level order traversal- imp
    //Recursive - Brute force

    public List<List<Integer>> levelOrderRecursion(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        int ht = heightHelper(root);
        for (int i = 1; i <= ht; i++) {
            List<Integer> current = new ArrayList<>();
            traverse(current, root, i);
            ans.add(current);
        }
        return ans;
    }

    static int heightHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightHelper(root.left), heightHelper(root.right));
    }

    static void traverse(List<Integer> current, TreeNode node, int level) { //In this problem we have a requirement to store output in list of list so we are passing a new current object for every level to store elements in that level.
        if (node == null) {
            return;
        }
        if (level == 1) {
            current.add(node.data);
            return;
        }
        traverse(current, node.left, level - 1);
        traverse(current, node.right, level - 1);

    }

    //Time complexity:
    // 1. O(n) to find height
    // 2. O(n^2) for actual traversal coz for every level we are going from that root to that level. So 5th level would be 1st to 5th and 6th will be 1st to 6th and for each and every node.
    // Ex: For a tree with n levels (and thus n nodes in the case of a skewed tree), you would have:
    //1 operation for the 1st level,
    //2 operations for the 2nd level,
    //...
    //n operations for the nth leve
    // Summing these up, you get a total of 1 + 2 + ... + n operations, which equals n(n+1)/2, simplifying to O(n^2) in terms of time complexity.

    //Think of it as doing DFS for each level but unlike standard DFS that explores all nodes in a go,this approach starts anew from root for each level.

    // For a balanced tree, while the height is log(n) and the number of nodes at each level doubles, the traversal from the root to each level still involves visiting all nodes on the path to nodes at that level.
    // The method used here does not take advantage of the balanced structure to reduce the complexity to O(n log n) because it unnecessarily traverses the upper levels for each node in the lower levels.

    //Space complexity:
    //1. We will not consider the space to store the output.
    //2. We need to store recursive calls to find the height. So height of a perfect tree will be O(log(n)) for a balanced tree and O(n) for a skewed tree.


    //Iterative using Queues
    //When we want to store output in list of lists

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        q.add(root);
        q.add(null);
        List<Integer> current = new ArrayList<>();
        while (!q.isEmpty()) {
            TreeNode element = q.remove();
            if (element == null) {
                ans.add(current);
                if (q.isEmpty()) {
                    return ans;
                }
                current = new ArrayList<>();
                q.add(null);
                continue;
            }
            current.add(element.data);
            if (element.left != null) {
                q.add(element.left);
            }
            if (element.right != null) {
                q.add(element.right);
            }
        }
        return ans;
    }

    //Here, null is used to track it is the end of the level.

    //Without null:
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        q.add(root);
        while (!q.isEmpty()) {
            List<Integer> current = new ArrayList<>();
            int count = q.size();
            for (int i = 0; i < count; i++) {
                TreeNode element = q.remove();
                current.add(element.data);
                if (element.left != null) {
                    q.add(element.left);
                }
                if (element.right != null) {
                    q.add(element.right);
                }
            }
            ans.add(current);
        }
        return ans;
    }

    //At any point, number of elements in the queue will correspond to number of elements in a level

    //Both the above methods are same in terms of complexity.

    //Time: O(n) -> visiting each node once.
    //Space: O(n) -> queue - max width of the tree (O(n/2) for perfect binary tree)

    //When we only want to print the data (https://www.geeksforgeeks.org/problems/level-order-traversal)
    static ArrayList<Integer> levelOrder3(TreeNode root) {
        // Your code here
        ArrayList<Integer> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.remove();
            list.add(node.data);
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
        return list;
    }

    public void Helper(TreeNode node, int level, List<List<Integer>> result) {
        if (result.size() == level)
            result.add(new ArrayList<Integer>());

        result.get(level).add(node.data);

        if (node.left != null)
            Helper(node.left, level + 1, result);
        if (node.right != null)
            Helper(node.right, level + 1, result);
    }

    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;
        Helper(root, 0, result);
        return result;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer> > ans = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null){
            return ans;
        }
        q.add(root);
        List<Integer> current;
        while(!q.isEmpty()){
            current = new ArrayList<>();
            int size = q.size();
            for(int i = 0; i<size; i++){
                TreeNode curr = q.poll();
                if(curr.left!=null){
                    q.offer(curr.left);
                }
                if(curr.right!=null){
                    q.offer(curr.right);
                }
                current.add(curr.data);
            }
            ans.add(0, current);
        }
        return ans;
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        LinkedList<List<Integer>> list = new LinkedList<List<Integer>>();
        addLevel(list, 0, root);
        return list;
    }

    private void addLevel(LinkedList<List<Integer>> list, int level, TreeNode node) {
        if (node == null) return;
        if (list.size()-1 < level) list.addFirst(new LinkedList<>());
        list.get(list.size()-1-level).add(node.data);
        addLevel(list, level+1, node.left);
        addLevel(list, level+1, node.right);
    }
    //Height balanced tree

    //Approach 1: Brute force
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = helperHeight(root.left);
        int right = helperHeight(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int helperHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    //This is a top down approach
    //Time: O(n^2) as we are accessing all nodes and then finding height for them also.

    //Approach 2: same as above but we will use a hashmap to store height as it avoids recalculating the height of subtrees
    public boolean isBalanced2(TreeNode root) {
        HashMap<TreeNode, Integer> map = new HashMap<>();
        if (root == null) {
            return true;
        }
        int left = helperHeight2(root.left, map);
        int right = helperHeight2(root.right, map);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int helperHeight2(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (!map.containsKey(root)) {
            map.put(root, 1 + Math.max(helperHeight2(root.left, map), helperHeight2(root.right, map)));
        }
        return map.get(root);
    }

    //DFS approach

    public boolean isBalanced3(TreeNode root) {
        return depth3(root) != -1;
    }

    public int depth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth3(root.left);
        int right = depth3(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    //Min depth of a binary tree

    //Max depth of a binary tree

    //Are two binary trees identical?
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) {
            return q == null;
        }
        if (q == null) {
            return p == null;
        }
        if (p.data != q.data) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    //Time: O(N) where N is the number of nodes in the smaller tree if they're different, or simply the number of nodes in the trees if they're the same size.
    //Space: O(min(H1, H2)), in best case, trees are balanced so it will be O(logn) but worst case both trees are identical and skewed O(n)
    //The recursion depth is determined by the height of the shorter tree in the worst case (since the algorithm would stop upon finding the first difference).

    public static TreeNode levelOrderSuccessor(TreeNode root, int val) {
        if (root == null) return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.left != null) {
                q.offer(curr.left);
            }
            if (curr.right != null) {
                q.offer(curr.right);
            }
            if (curr.data == val) {
                if (!q.isEmpty()) {
                    return q.poll();
                }
                return null;
            }
        }

        return null;
    }
    //Time: O(n)
    //Space: O(w) - max width of the tree => O(n/2) for perfect binary tree no of lead nodes will be n/2 => O(n)

    //https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null)
            return list;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            boolean even = list.size()%2==0;
            List<Integer> currentList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (even) {
                    currentList.add(curr.data);
                }
                else{
                    currentList.add(0, curr.data);
                }
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
            list.add(currentList);
        }
        return list;
    }

    //Approach 2: actual zigzag traversal
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null)
            return list;
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean reverse=true;;
        while (!q.isEmpty()) {
            int size = q.size();
            reverse = !reverse;
            List<Integer> currentList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                if (!reverse) {
                    TreeNode curr = q.pollFirst();
                    if (curr.left != null) {
                        q.addLast(curr.left);
                    }
                    if (curr.right != null) {
                        q.addLast(curr.right);
                    }
                    currentList.add(curr.data);
                }
                else{
                    TreeNode curr = q.pollLast();
                    if(curr.right!=null){
                        q.offerFirst(curr.right);
                    }
                    if(curr.left!=null){
                        q.offerFirst(curr.left);
                    }
                    currentList.add(curr.data);
                }
            }
            list.add(currentList);
        }
        return list;
    }

    //Populating Next Right Pointers in Each Node - https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
    //BFS

    public Node connect(Node root) {
        if(root==null) return root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                Node curr = q.poll();
                if(i!=size-1){
                    curr.next = q.peek();
                }
                if(curr.left!=null){
                    q.offer(curr.left);
                }
                if(curr.right!=null){
                    q.offer(curr.right);
                }
            }
        }
        return root;
    }

    //Follow up:
    //Do it without using a queue

    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        q.add(root);
        int i;
        while (!q.isEmpty()) {
            int size = q.size();
            for (i = 0; i < size; i++) {
                TreeNode curr = q.remove();
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
                if(i==size-1){
                    ans.add(curr.data);
                }
            }
        }
        return ans;
    }
    //Time: O(n)
    //Space: O(n) for queue

    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideViewDFSHelper(root, list, 0);
        return list;
    }

    public void rightSideViewDFSHelper(TreeNode root, List<Integer> list, int depth){
        if(root==null){
            return;
        }
        if(depth<list.size()){
            list.set(depth, root.data);
        }
        else{
            list.add(root.data);
        }
        rightSideViewDFSHelper(root.left, list, depth+1);
        rightSideViewDFSHelper(root.right, list, depth+1);
    }
    //Time: O(n) all nodes will be traversed once.
    //Space: O(n) for a skewed tree (O(n) for recursion stack and O(n) for list for skewed tree if we are considering output storage.)

    //Approach 2: root -> right -> left traversal

    public void rightSideViewDFSHelper2(TreeNode root, List<Integer> list, int depth){
        if(root==null){
            return;
        }
        if(depth==list.size()){
            list.add(root.data);
        }
        rightSideViewDFSHelper2(root.right, list, depth+1); //right first as we want right side view
        rightSideViewDFSHelper2(root.left, list, depth+1);
    }

    //print left view
    static int maxLevel;

    public static void printLeftView(TreeNode root) {
        view(root, 0);
    }

    public static void view(TreeNode root, int currDepth ){
        if(root==null){
            return;
        }
        if(currDepth==maxLevel){
            maxLevel++;
            System.out.print(root.data + " ");
        }
        view(root.left, currDepth+1);
        view(root.right, currDepth+1);
    }

    static int max = 0;

    public static int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max-1; // The maximum length is number of nodes - 1 (edges), hence subtract 1
    }

    public static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);

        max = Math.max(max, 1 + leftDepth + rightDepth); // consider diameter through current node and update max if it
        // happens to exceed max

        return 1 + Math.max(leftDepth, rightDepth); // max of left or right as we need to consider the current node for
        // height
    }

    int maxSumVal = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return max;
    }

    public int maxSum(TreeNode root){
        if(root==null){
            return 0;
        }

        int leftSum = Math.max(0, maxSum(root.left));
        int rightSum = Math.max(0, maxSum(root.right));

        maxSumVal = Math.max(maxSumVal, root.data + leftSum + rightSum);

        return root.data + Math.max(leftSum, rightSum);
    }

    public TreeNode invertTree1(TreeNode root) {
        invert1(root);
        return root;
    }

    public void invert1(TreeNode root){
        if(root==null){
            return;
        }
        invert1(root.left);
        invert1(root.right);
        TreeNode tempLeft = root.left;
        TreeNode tempRight = root.right;
        root.left = tempRight;
        root.right = tempLeft;
        return;
    }
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //Time & Space: O(n)
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
