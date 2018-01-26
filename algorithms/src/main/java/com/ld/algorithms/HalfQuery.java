package com.ld.algorithms;


public class HalfQuery {
	
	public static int rank(int[] arr,int num,int start,int end){
		int m =  (end + start)/2;
		int p = 0;
		
		if(m < 1) return m; 
		
		if (arr[m] == num){
			p = m;
		}
		else if(arr[m] > num){
			p = rank(arr, num,start,m);
		}
		else if(arr[m] < num){
			p = rank(arr, num, m, end);
		}
		return p;
	}


}
