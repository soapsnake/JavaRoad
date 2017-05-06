package com.ld.observer.observer;

import com.ld.observer.object.ObjectConcreateImpl;
	
/**
 * 
 * @author Liu.Dun
 * 观察者对象
 *
 */
public class ObserverConcreateImpl implements ObserverConcreate{
	private String observerName;
	
	private String receiveContent;
	
	
	public String getObserverName() {
		return observerName;
	}

	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}
	
	/**
	 * 拉模式,观察者在收到通知提示后,会自己到目标中去获取实际的内容
	 */
	public void remind(Object o) {
		receiveContent = ((ObjectConcreateImpl)o).getObjectContent();
		System.out.println(observerName+"同学,PS4价格已经降到"+receiveContent+"啦,还不快买他妈的?");
	}



}
