package com.soapsnake.pattern.structurals.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {
	private List<Component> childrens;

	public Composite() {
		childrens = new ArrayList<>();
	}

	@Override
	public void operation() {
		for (Component children : childrens) {
			children.operation();
		}
	}

	public void add(Component c) {
		childrens.add(c);
	}

	public  void remove(Component c) {
		childrens.remove(c);
	}

	@Override
	public Component getChild(int i) {
		return childrens.get(i);
	}
}
