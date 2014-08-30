package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafePreto {

	private ComponentsFactory factory;
	public CafePreto(ComponentsFactory factory){
	this.factory = factory;
	}
	public boolean blackPlan(){
	if (!factory.getCupDispenser().contains(1)) {
		GerenteDeMenssagens.setWarnMessage(Messages.OUT_OF_CUP);
	return false;
	}
	if (!factory.getWaterDispenser().contains(0.5)) {
		GerenteDeMenssagens.setWarnMessage(Messages.OUT_OF_WATER);
	return false;
	}
	if (!factory.getCoffeePowderDispenser().contains(0.8)) {
		GerenteDeMenssagens.setWarnMessage(Messages.OUT_OF_COFFEE_POWDER);
	return false;
	}
	return true;
	}
	public void blackMix(){
	factory.getCoffeePowderDispenser().release(0.6);
	factory.getWaterDispenser().release(0.9);
	}
	public ComponentsFactory getFactory(){
	return factory;
	}
	public void drinkRelease(){
	factory.getCupDispenser().release(1);
	factory.getDrinkDispenser().release(0.3);
	}

}
