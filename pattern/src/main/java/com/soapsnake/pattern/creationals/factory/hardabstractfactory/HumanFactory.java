package com.soapsnake.pattern.creationals.factory.hardabstractfactory;

import com.soapsnake.pattern.creationals.factory.Human;

public interface HumanFactory {

	Human createYellow();

	Human createBlack();

	Human createWhite();
}
