package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;


public class White_Sugar extends Bebida{

	private ComponentsFactory fac;
	private MyCoffeeMachine coffe = new MyCoffeeMachine();
	
	
	
	@Override
	public void chamaDrink() {
		fac.getCupDispenser().contains(1);
		fac.getWaterDispenser().contains(3.0);
		fac.getCoffeePowderDispenser().contains(3.0);
		fac.getCreamerDispenser().contains(3.0);
		fac.getSugarDispenser().contains(3.0);

		coffe.planejamento(coffe.calculaTroco());	
		
		fac.getDisplay().info(Messages.MIXING);
		fac.getCoffeePowderDispenser().release(3.0);
		fac.getWaterDispenser().release(3.0);
		fac.getCreamerDispenser().release(3.0);
		fac.getSugarDispenser().release(3.0);
		
		fac.getDisplay().info(Messages.RELEASING);
		fac.getCupDispenser().release(1);
		fac.getDrinkDispenser().release(3.0);
		fac.getDisplay().info(Messages.TAKE_DRINK);
		
		coffe.retornarTroco(coffe.calculaTroco());
		
		fac.getDisplay().info(Messages.INSERT_COINS);
	}

}
