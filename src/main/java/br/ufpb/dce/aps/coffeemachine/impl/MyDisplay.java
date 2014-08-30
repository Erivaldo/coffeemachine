package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Display;

public class MyDisplay implements Display{
	private Display myDisplay;
	public MyDisplay(ComponentsFactory factory){
	myDisplay = factory.getDisplay();
	}
	
	public void warn(String msg) {
	myDisplay.warn(msg);
	}
	public void info(String insertCoins) {
		myDisplay.info(insertCoins);
}
}