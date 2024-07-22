package org.example.sorting;

public class MergeSort {
    private void merge(int arr[], int l, int m, int r)
    {
        int[] temp = new int[r-l+1];
        int left = l;
        int right = m + 1;
        int k = 0;
        while(left<=m && right<=r){
            if(arr[left]<=arr[right]){
                temp[k++] = arr[left++];
            }
            else{
                temp[k++] = arr[right++];
            }
        }
        while(right<=r){
            temp[k++] = arr[right++];
        }
        while(left<=m){
            temp[k++] = arr[left++];
        }
        for(int i = 0; i<k; i++){
            arr[l] = temp[i];
            l++;
        }
    }
    public void mergeSort(int arr[], int l, int r)
    {
        if(l>=r){
            return;
        }
        int mid = l + (r-l)/2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid+1, r);
        merge(arr, l, mid, r);
    }
}
