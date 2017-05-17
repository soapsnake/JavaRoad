package com.ld.generic;

import java.util.Collection;

public class VarargsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] is = {1,23,32,21,232,32,21};
		
		m(is,"liud","zhansdsa","asdsada","asdsada");
	}
	
	public static void m(Object object,String... names){
		System.out.println(object);
		for(String name:names){
			System.out.println(name);
		}
	}
	
	@SafeVarargs
	public static <T> void m2(Collection<T> coll,T... ts){   //泛型集合,泛型可变长参数
		for(T t:ts)coll.add(t);
	}
	
	@SafeVarargs
	public static <E> E[] array(E... array){   //泛型数组
		return array;
		}
	
	@SuppressWarnings("unchecked")
	public static <T extends Throwable> void throwAs(Throwable e) throws T{  //抛出泛型异常
		throw(T) e;
	}
	
	
	
	
	
	
	
	
	
	

}
