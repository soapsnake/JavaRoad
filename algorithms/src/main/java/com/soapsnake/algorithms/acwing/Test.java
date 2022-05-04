package com.soapsnake.algorithms.acwing;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test{


    public static void main(String[] args) throws NumberFormatException, IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        
        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];
        
        String[] res = br.readLine().split(" ");
        for (int i = 0; i < num; i ++) {
            arr[i] = Integer.parseInt(res[i]);
        }
        
        quickSort(arr, 0, num - 1);
        br.close();
    }

    private static void quickSort(int[] arr, int l, int r) {
        if(l >= r) {
            return;
        }
        int mid = arr[l + r >> 1];
        int i = l - 1;
        int j = r + 1;
        
        while(i < j){
            do i++; while (arr[i] < mid);
            do j--; while (arr[i] > mid);
            if(i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        quickSort(arr, l, j);
        quickSort(arr, j + 1, r);
    }
}
