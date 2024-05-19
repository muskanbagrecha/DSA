package org.example;

import java.util.*;

public class LinkedListProblems {

    //    https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/
    //Solution 1:
    public Node deleteMiddle(Node head) {
        int size = 0;
        Node temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        int middle = size / 2;
        int current = 0;
        if (middle == 0) {
            return null;
        }
        temp = head;
        Node prev = temp;
        while (current != middle) {
            prev = temp;
            temp = temp.next;
            current++;
        }
        prev.next = temp.next;
        return head;
    }
    //O(n) but this is two pass solution: one to find the size, then to remove from mid

    //Solution 2
    public Node deleteMiddle2(Node head) {
        if (head == null || head.next == null) { //0 or 1 elements, we need to return null
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }
    //Hare and Turtle : O(n) - One pass solution
    //Why this works?
    //We have to have the check of head==null or head.next==null so we can straightaway return null as n: 0 => null, n:1 => 1/2 = 0 so we have to delete first element
    //1. Slow is moving at half of the pace of fast, so by the time fast reaches the end, slow will be at mid.
    //2. For two elements, fast will be null and we have to remove the first element. So while loop will never execute and 0th node will point to slow.next.next which is null.
    //3. For three elements, fast will be last element, while loop will never execute as fast.next will be null. So slow.next = slow.next.next will end up making first node point to last element(fast).
    //4. For odd elements, while will execute, fast will point to last element & slow will end up pointing to one index less than the mid so we can remove the next element
    //5. For even elements, while will execute, fast will end up becoming null, slow will point to one element left of element to be removed.

    //https://leetcode.com/problems/middle-of-the-linked-list/description/
    //Similar problem
    public static Node middleNode(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //https://leetcode.com/problems/delete-node-in-a-linked-list
    public void deleteNode(ListNode node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }

    //Important to practice

    //Reverse a singly linked list which has only head

    //Solution 1: Using extra space (arr) to store the elements, we then iterate through the LL again and copy the elements from the end of the array.
    //Use stack instead of arraylist so we pop elements in reversed order by default
    public static Node reverseList(Node head) {
        ArrayList arr = new ArrayList();
        Node temp = head;
        while (temp != null) {
            arr.add(temp.data);
            temp = temp.next;
        }
        System.out.println(arr);
        temp = head;
        while (temp != null && arr.size() > 0) {
            int index = arr.size() - 1;
            temp.data = arr.get(index);
            arr.remove(index);
        }
        return head;
    }

    //Time complexity: O(n) - two passes
    //Space complexity: O(n)
    public static Node reverseList2(Node head) {
        Node temp = head;
        Node prev = null;
        Node front;
        while (temp != null) {
            front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }
//    Time: O(n) single pass
//    Space: O(1)

    //Recursion
    public Node reverseRecursion(Node head, Node prev) {
        if (head == null) {
            return prev;
        }
        Node front = head.next;
        head.next = prev;
        return reverseRecursion(front, head);
    }
    //    Time: O(n)
    //    Space: O(n) - stack

    public static Node reverseBetween(Node head, int left, int right) {
        List<Node> list = new ArrayList<>();
        int ctr = 1;
        Node temp = head;
        while (ctr <= right) {
            if (ctr >= left) {
                list.add(temp);
            }
            temp = temp.next;
            ctr++;
        }

        ctr = 1;
        temp = head;
        while (ctr <= right) {
            if (ctr >= left) {
                temp.data = list.get(list.size() - 1).data;
                list.remove(list.size() - 1);
            }
            temp = temp.next;
            ctr++;
        }
        return head;
    }

    //Time: O(n) - two passes
    //Space: O(n) - at max when whole list needs to be reversed

//    https://leetcode.com/problems/linked-list-cycle/

    //Brute force
    public static boolean hasCycle1(Node head) {
        if (head == null) {
            return false;
        }
        HashSet<Node> mySet = new HashSet<>();
        Node temp = head;
        while (temp != null) {
            if (mySet.contains(temp)) {
                return true;
            }
            mySet.add(temp);
        }
        return false;
    }

    //Space: O(n)
    //Time: O(n)

    //Optimal
    public boolean hasCycle2(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    //Hare and tortoise solution
    //Time: O(n)
    //Space: O(1)

    //Time Complexity (O(n)): The fast pointer moves twice as fast as the slow pointer, so if there's no cycle, the fast pointer will reach the end in n/2 steps. If a cycle exists, the pointers will meet within the cycle at some point, also resulting in O(n) time complexity.


    //https://leetcode.com/problems/linked-list-cycle-ii/description/

    //Detect starting point of the cycle

    //Naive:
    public Node detectCycle(Node head) {

        Node temp = head;
        HashSet<Node> set = new HashSet<>();
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            set.add(temp);
            temp = temp.next;
        }
        return null;
    }

    //Time: O(n)
    //Space: O(n)

    //Optimal


    //https://www.codingninjas.com/studio/problems/find-length-of-loop_8160455

    //Naive:
    public static int lengthOfLoop(Node head) {
        // Write your code here

        Node temp = head;
        HashMap<Node, Integer> map = new HashMap<>();
        int ctr = 0;
        while (temp != null) {
            if (map.containsKey(temp)) {
                return ctr - map.get(temp);
            }
            map.put(temp, ctr);
            ctr++;
            temp = temp.next;
        }
        return 0;
    }

    //Time: O(n)
    //Space: O(n)

    //Optimal
    public static int lengthOfLoop2(Node head) {
        // Write your code here

        if (head == null || head.next == null) {
            return 0;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return 0;
        }
        slow = slow.next;
        int ctr = 1;
        while (slow != fast) {
            slow = slow.next;
            ctr++;
        }
        return ctr;
    }
    //Time: O(n)
    //Space: O(1)

    //https://leetcode.com/problems/palindrome-linked-list/

    //Idea:
    //1. Traverse to the middle of the list. Using hare and turtle approach.
    //2. If list has even elements, slow will point to the 1st element of the two mid elements.
    //3. If list has odd elements, slow will point to the mid.
    //4. Start iteration from head to slow (mid) and push elements in stack
    //5. If odd elements are there, we dont need to check for the mid element, so we will check fast!=null which means list has odd elements so we will move temp to next element.
    // Above step works as fast always points to null when elements are even
    //6. Then preOrderTraversal in the second half of the array (IMP: till null not fast) and compare with top of stack for palindrome)

    //Time: half + half + half iterations in the list which simplified to O(1.5n) => O(n)
    //Space: O(n/2) for even O((n-1)/2) for odd which simplifies to O(n)

    //Naive 2:
    public boolean isPalindrome2(Node head) {
        Node temp = head;
        Stack<Integer> stack = new Stack<>();
        while (temp != null) {
            stack.push((Integer) temp.data);
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            if (stack.pop() != temp.data) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    //Instead of comparing first half of linked list with second half, we will just reverse it and compare with the original LL.
    //1 2 3 4 3 2 1 reversed is 1 2 3 4 3 2 1 and since they are equal it is palindrome.
    //Conclusion palindrome can be checked if original == reverse

    //Time: O(n) + O(n) => O(n)
    //Space: O(n)

    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node prev = slow;
        Node temp = slow.next;
        Node front;
        slow = head;
        while (temp != null) {
            front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        while (slow != prev && slow.next != prev) {
            if (slow.data != prev.data || slow.next == prev) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }

    //Without using extra space.

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = reverse(slow);
        slow = head;
        while (fast != null) { //we can just check fast as for odd no of elements reversed list (Second half) will have one less element than first half of the list as mid is second element in even no of elements.
            if (slow.data != fast.data) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
//In interview suggesting reversing second half again to restore the LL.

    public ListNode reverse(ListNode head, ListNode prev) {
        if (head == null) {
            return prev;
        }
        ListNode temp = head.next;
        head.next = prev;
        prev = head;
        return reverse(temp, prev);
    }

    public static Node addOne2(Node head) {
        //code here.
        head = reverseList2(head);
        int carry = 1;
        Node<Integer> temp = head;
        while (carry != 0 && temp != null) {
            temp.data += carry;
            if (temp.data < 10) {
                carry = 0;
            } else {
                temp.data = 0;
                carry = 1;
                temp = temp.next;
            }
        }
        head = reverseList2(head);
        if (carry == 0) return head;
        Node newNode = new Node(1);
        newNode.next = head;
        head = newNode;
        return head;
    }

    //TC: O(3N)
    //SC: O(1)

    public static Node addOneRecursive(Node<Integer> head) {
        //code here.
        int[] carryWrapper = {1};
        add(head, carryWrapper);
        if (carryWrapper[0] == 1) {
            Node temp = new Node(1);
            temp.next = head;
            head = temp;
        }
        return head;
    }

    public static void add(Node<Integer> head, int[] carryWrapper) {
        if (head.next != null) {
            add(head.next, carryWrapper);
        }
        if (carryWrapper[0] == 0) {
            return;
        }
        head.data += 1;
        if (head.data >= 10) {
            head.data = 0;
        } else {
            carryWrapper[0] = 0;
        }
    }
    //TC: O(N)
    //Space: O(N)

    public static Node addOne(Node<Integer> head) {
        //code here.
        int carry = add(head);
        if (carry == 1) {
            Node temp = new Node(1);
            temp.next = head;
            head = temp;
        }
        return head;
    }

    public static int add(Node<Integer> head) {
        if (head == null) {
            return 1;
        }
        int carry = add(head.next);
        head.data += carry;
        if (head.data >= 10) {
            head.data = 0;
            return 1;
        }
        return 0;
    }

    //Remove nodes from LL
    //https://leetcode.com/problems/remove-nodes-from-linked-list
    public Node removeNodes(Node head) {
        Node<Integer> temp = head;
        Stack<Node<Integer>> s = new Stack<>();
        while (temp != null) {
            while (!s.isEmpty() && s.peek().data < temp.data) {
                s.pop();
            }
            s.push(temp);
            temp = temp.next;
        }
        if (s.isEmpty()) return null;

        temp = null;
        while (!s.isEmpty()) {
            Node node = s.pop();
            node.next = temp;
            temp = node;
        }
        return temp;
    }
    //TC: O(n) for stack pushing and popping. Then O(k) for constructing new Ll where K is total elements which do not have a greater element after it.
    //Space: O(n) worst case all elements need to be stored in stack.
    //Above approach is slightly slow.

    //https://leetcode.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        ListNode tempA = headA;
        while (tempA != null) {
            lenA++;
            tempA = tempA.next;
        }
        ListNode tempB = headB;
        while (tempB != null) {
            lenB++;
            tempB = tempB.next;
        }
        tempA = headA;
        tempB = headB;
        while (lenA > lenB) {
            tempA = tempA.next;
            lenA--;
        }
        while (lenB > lenA) {
            tempB = tempB.next;
            lenB--;
        }
        while (tempA != null) { // at this point length from start of temp to end is equal
            if (tempA == tempB) {
                return tempA;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return null;
    }
    //assume, N1 is length of list A, N2 is length of list B
    //TC: O(N1) + O(N2) + O(N1-N2) + O(N2) => O(2N1 + N2)
    //SC: O(1)
    //Optimized:
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != tempB) { //no need to check both tempA and tempB are null then return no intersection and if they are both null it is going to terminate the while loop and a will be null
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }
        return tempA;
    }
    //TC: O(N1+N2)
    //sc: O(1)
}
