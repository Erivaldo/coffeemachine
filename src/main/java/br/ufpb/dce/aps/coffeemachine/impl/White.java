package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;



public class White extends Bebida{
	ComponentsFactory fac;
	Messages messages;
	@Override
	public void chamaDrink() {
		fac.getCupDispenser().contains(1);
		fac.getWaterDispenser().contains(3.0);
		fac.getCoffeePowderDispenser().contains(3.0);
		fac.getCreamerDispenser().contains(3.0);
		
		fac.getDisplay().info(messages.MIXING);
		fac.getCoffeePowderDispenser().release(3.0);
		fac.getWaterDispenser().release(3.0);
		fac.getCreamerDispenser().release(3.0);
		
		fac.getDisplay().info(messages.RELEASING);
		fac.getCupDispenser().release(1);
		fac.getDrinkDispenser().release(3.0);
		fac.getDisplay().info(messages.TAKE_DRINK);
		
		fac.getDisplay().info(messages.INSERT_COINS);
	}
}
