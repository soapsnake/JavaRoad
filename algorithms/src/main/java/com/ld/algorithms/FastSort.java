package com.ld.algorithms;

public class FastSort {
	
	public static int[] fastSort(int[] arr,int start,int end){
		int i = start;
		int key = start;
		int j = end;
		int temp=0;
		
		while(i < j){
			while(arr[i] < arr[key] && i < j){
				i++;
			}
			temp = arr[i];
			
			while(arr[j] >arr[key] && j > i){
				j--;
			}
			arr[i] = arr[j];
			arr[j] = temp;
		}
		arr[i] = arr[key];
		
		if(i-1 > start){fastSort(arr, start, i-1);}
		
		if(i+1 < end){fastSort(arr,i+1,end);}
		
		return arr;
	}

}
