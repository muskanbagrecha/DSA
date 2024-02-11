import org.junit.Assert;
import org.junit.Test;
import org.example.Sort;

public class SortTest {

    @Test
    public void testIterativeBubbleSort() {
        int[] arr = {5, 3, 2, 4, 1};
        Sort.bubbleSort(arr, arr.length);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testRecursiveBubbleSort() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        Sort.bubbleSort2(arr, arr.length);
        Assert.assertArrayEquals(new int[]{11, 12, 22, 25, 34, 64, 90}, arr);
    }

    @Test
    public void testIterativeSelectionSort() {
        int[] arr = {29, 10, 14, 37, 13};
        Sort.selectionSort(arr);
        Assert.assertArrayEquals(new int[]{10, 13, 14, 29, 37}, arr);
    }

    @Test
    public void testRecursiveSelectionSort() {
        int[] arr = {20, 12, 10, 15, 2};
        Sort.selectionSort2(arr, arr.length);
        Assert.assertArrayEquals(new int[]{2, 10, 12, 15, 20}, arr);
    }
}
