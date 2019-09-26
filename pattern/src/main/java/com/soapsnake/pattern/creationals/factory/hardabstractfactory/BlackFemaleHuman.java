package com.soapsnake.pattern.creationals.factory.hardabstractfactory;

import com.soapsnake.pattern.creationals.factory.Human;

public class BlackFemaleHuman implements Human {
	@Override
	public String getColor() {
		return null;
	}

	@Override
	public String sayHello() {
		return null;
	}

	@Override
	public String getSex() {
		return "Femal";
	}
}
