package com.soapsnake.pattern.creationals.factory.simpleabstractfactory;

import com.soapsnake.pattern.creationals.factory.Human;
import com.soapsnake.pattern.creationals.factory.YellowHuman;

public class YellowManFactory extends AbstractFactory {

	public YellowManFactory() {
	}

	@Override
	public final Human createHuman() {
		return new YellowHuman();
	}
}
