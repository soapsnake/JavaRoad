package com.soapsnake.pattern.structurals.strategypattern.springresourceloader;

public class Client {

	public static void main(String[] args) {

		ResourceLoaderContext context = new DefaultResourceLoaderContext(new ClassPathResourceLoader());
		context.loadResource();   //实际的load工作是ClassPathResourceLoader做的,context只是提供工作环境
		context.doOthers();    //context除了提供loadResource功能,还会提供其他一些功能
	}
}
