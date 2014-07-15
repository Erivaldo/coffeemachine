package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class MyCoffeeMachine implements CoffeeMachine{
	protected int total ; 
	protected ComponentsFactory fac;
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		fac = factory;
		fac.getDisplay().info("Insert coins and select a drink!");
		// TODO Auto-generated constructor stub
	}

	public void insertCoin(Coin dime) {
		// TODO Auto-generated method stub
		total += dime.getValue();
		fac.getDisplay().info("Total: US$ " + total/100 + "." + total%100);
		
	}
	
	
	
	
	
}
