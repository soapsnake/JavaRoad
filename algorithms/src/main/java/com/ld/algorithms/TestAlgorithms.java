package com.ld.algorithms;


/**
 * 
 * @author kevin
 *this class is dedicated to test some Algorithms
 */
public class TestAlgorithms {

	public static void main(String[] args) {
		int[] arr = {122,86,34,24,45,78,7,55,12,90};
		System.out.println("排序前的数组为： "+printArr(arr));
		
		int[] srotedArr1 = FastSort.fastSort(arr, 0, arr.length-1);
		System.out.println("排序后的数组为： "+printArr(srotedArr1));
		
		System.out.println("数字在数组中的位置为: arr["+ HalfQuery.rank(srotedArr1, 78, 0, srotedArr1.length)+"]");
		
	}
	
	public static String printArr(int[] arr){
		if(arr == null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<arr.length;i++){
			sb.append(arr[i]);
			if(i != arr.length-1){
				sb.append(",");
			}
		}
		return sb.toString();
	}
	

}
