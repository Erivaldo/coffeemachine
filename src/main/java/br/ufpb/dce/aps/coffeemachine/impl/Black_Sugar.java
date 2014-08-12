package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;


public class Black_Sugar extends Bebida{
	
	public ComponentsFactory fac;
	public MyCoffeeMachine coffe = new MyCoffeeMachine();
	
	@Override
	public void chamaDrink() {
		
		if(!(fac.getCupDispenser().contains(1))){
			fac.getDisplay().warn(Messages.OUT_OF_CUP);
			coffe.removerMoeda();
			fac.getDisplay().info("Insert coins and select a drink!");
			return;
		}
		fac.getWaterDispenser().contains(3.0);
		fac.getCoffeePowderDispenser().contains(3.0);
		if(!(fac.getSugarDispenser().contains(1.0))){
			fac.getDisplay().warn(Messages.OUT_OF_SUGAR);
			coffe.removerMoeda();
			fac.getDisplay().info("Insert coins and select a drink!");
			return;
		}
			
	
		fac.getDisplay().info("Mixing ingredients.");
		fac.getCoffeePowderDispenser().release(3.0);
		fac.getWaterDispenser().release(3.0);
		fac.getSugarDispenser().release(2.0);
		fac.getDisplay().info("Releasing drink.");
			
		fac.getCupDispenser().release(1);
		fac.getDrinkDispenser().release(3.0);
		fac.getDisplay().info("Please, take your drink.");
		fac.getDisplay().info("Insert coins and select a drink!");
		
			
		coffe.coins.clear();
	}

}
