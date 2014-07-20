package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class MyCoffeeMachine implements CoffeeMachine{
	protected int total ; 
	protected ComponentsFactory fac;
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		fac = factory;
		fac.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin dime) {
		if (dime == null) {
			throw new CoffeeMachineException("Insert null coin");
		}else{
			total += dime.getValue();
			fac.getDisplay().info("Total: US$ " + total/100 + "." + total%100);
		}
		
		
	}

	public void cancel() {
		if (this.total == 0) {
			throw new CoffeeMachineException(" Cancel without inserting coins");
		}
	}
	
	
	
	
	
}
