package com.ld.observer.object;

/**
 * 目标抽象类,抽象方法:注册和通知,均由子类负责实现
 */
import com.ld.observer.observer.ObserverConcreate;

public abstract class ObjectConcreate {
	
	private String objectContent;
	
	public abstract void registorObserver(ObserverConcreate observer);
	
	public abstract void notifyObserver();
	
	public String getObjectContent() {
		return objectContent;
	}

	public void setObjectContent(String objectContent) {
		this.objectContent = objectContent;
	}

	
}
