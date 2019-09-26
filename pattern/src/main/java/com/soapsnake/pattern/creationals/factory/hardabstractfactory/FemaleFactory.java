package com.soapsnake.pattern.creationals.factory.hardabstractfactory;

import com.soapsnake.pattern.creationals.factory.Human;

public class FemaleFactory implements HumanFactory {


	@Override
	public Human createYellow() {
		return new YellowFemaleHuman();
	}

	@Override
	public Human createBlack() {
		return new BlackFemaleHuman();
	}

	@Override
	public Human createWhite() {
		return new WhiteFemaleHuman();
	}
}
