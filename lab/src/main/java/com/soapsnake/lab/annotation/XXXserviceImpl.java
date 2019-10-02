package com.soapsnake.lab.annotation;

public class XXXserviceImpl implements XxService {

	@Override
	@TestMethodAnnotation(field1 = false, filed2 = "hello fuck", fuckAction = {"gan", "cao", "cha" ,"jian"})
	public void print(@TestParamAnnotation("fuck them all") @TestParamAnnotation2("fuck harder!!!!") boolean fuck,
	                  @TestParamAnnotation2("fuck again and again...") String fuckName) {
		System.out.println("🚀x🚀🚀🚀🚀🚀🚀🚀🚀xxservice de print()" + fuck);
	}

	@Override
	public void doother() {
		System.out.println("do other");
	}

}
