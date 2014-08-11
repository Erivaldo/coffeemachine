package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;

public class Black extends Drink{

	@Override
	public void chamaDrink() {
		if(!(fac.getCupDispenser().contains(1))){
			fac.getDisplay().warn(Messages.OUT_OF_CUP);
			removerMoeda();
			fac.getDisplay().info("Insert coins and select a drink!");
			return;
		}
		if(!(fac.getWaterDispenser().contains(3.0))){
			fac.getDisplay().warn(Messages.OUT_OF_WATER);
			removerMoeda();
			fac.getDisplay().info("Insert coins and select a drink!");
			return;
		}
		if(!(fac.getCoffeePowderDispenser().contains(3.0))){
			fac.getDisplay().warn(Messages.OUT_OF_COFFEE_POWDER);
			removerMoeda();
			fac.getDisplay().info("Insert coins and select a drink!");
			return;
		}
			
		fac.getDisplay().info("Mixing ingredients.");
		fac.getCoffeePowderDispenser().release(3.0);
			
		fac.getWaterDispenser().release(3.0);
		fac.getDisplay().info("Releasing drink.");
			
		fac.getCupDispenser().release(1);
		fac.getDrinkDispenser().release(3.0);
		fac.getDisplay().info("Please, take your drink.");
		fac.getDisplay().info("Insert coins and select a drink!");
	}
	
}

	
	
		
