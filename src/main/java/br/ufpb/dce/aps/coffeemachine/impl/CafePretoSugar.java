package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafePretoSugar extends CafePreto {
	public CafePretoSugar(ComponentsFactory factory){
		super(factory);
		}
	public boolean blackSugarPlan(){
		if (!blackPlan()) { //verifyBlackPlan(getCupDispenser(), getWaterDispenser(), getCoffeePowderDispenser()
		return false;
		}
		if (!getFactory().getSugarDispenser().contains(5)) { // inOrder.verify(getSugarDispenser).contains(anyDouble());
			GerenteDeMenssagens.setWarnMessage(Messages.OUT_OF_SUGAR);
		return false;
		}
		return true;
		}
		public void blackSugarMix(){
		blackMix(); //blackMix(coffeePowderDispenser.release,waterDispenser).release )
		getFactory().getSugarDispenser().release(5); //inOrder.verify(sugarDispenser).release
		}

}
