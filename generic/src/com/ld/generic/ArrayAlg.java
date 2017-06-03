package com.ld.generic;

public class ArrayAlg {
	
	public static com.ld.generic.Pair<String> minmax(String[] a){
		if(a==null || a.length==0) return null;
		
		String min = a[0];
		String max = a[0];
		for(int i=1;i<a.length;i++){
			if(min.compareTo(a[i]) > 0) min = a[i];
			if(max.compareTo(a[i]) < 0) max = a[i];
		}
		return new Pair<String>(min,max);
	}
	
	public static <T> T getMiddle(T... a){
		return a[a.length/2];
	}
	
	
	public static void tripleValue(double x){
		x = 3*x;
	}
							//声明返回值类型的时候必须声明成:泛型变量<> 泛型类    的形式
	public static <T extends Comparable> Pair<T> minmax2(T[] a){   //这里用了extends关键字没有用implement
		if(a == null || a.length == 0) return null ;
		T min = a[0];
		T max = a[0];
		for(int i=1;i<a.length;i++){
			if(min.compareTo(a[i])>0) min = a[i];
		    if(max.compareTo(a[i])<0) max = a[i];
		}
			return  new Pair<T>(min,max);
	}
	
}
