package stacks;

import org.example.stacks.MyStack;
import org.junit.Assert;
import org.junit.Test;
import java.util.EmptyStackException;

public class MyStackTest {

    @Test
    public void testPushAndPeek() {
        MyStack<Integer> stack = new MyStack<>(2);
        stack.push(1);
        stack.push(2);
        Assert.assertEquals(Integer.valueOf(2), stack.peek());
    }

    @Test
    public void testPop() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        Assert.assertEquals(Integer.valueOf(1), stack.pop());
        Assert.assertTrue(stack.isEmpty());
    }

    @Test(expected = EmptyStackException.class)
    public void testPopEmptyStack() {
        MyStack<Integer> stack = new MyStack<>();
        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void testPeekEmptyStack() {
        MyStack<Integer> stack = new MyStack<>();
        stack.peek();
    }

    @Test
    public void testResize() {
        MyStack<Integer> stack = new MyStack<>(1);
        stack.push(1);
        stack.push(2); // Should trigger resize
        Assert.assertEquals(Integer.valueOf(2), stack.peek());
        Assert.assertEquals(2, stack.top + 1); // top is index, so size is top + 1
    }
}